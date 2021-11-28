package com.example.WebLab3.beans;

import com.example.WebLab3.beans.dto.HitDTO;
import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.ServiceManagerInterface;
import com.example.WebLab3.utility.ServiceManager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.kopitubruk.util.json.JSONUtil;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.*;

@Data
@ManagedBean
@SessionScoped
public class HitResults {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Hit newHit = new Hit();
    private final ServiceManagerInterface serviceManager = new ServiceManager();
    private String sessionId;
    private List<Hit> hitList = new ArrayList<>();

    @ManagedProperty(value = "#{hitDTO}")
    private HitDTO hitDTO;

    @PostConstruct
    private void initialUserSessionId() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        sessionId = session.getId();
    }

    public void addClick() {
        logger.info("Click detected!");

        String x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        Hit newClick = new Hit();
        newClick.setX(Double.valueOf(x));
        newClick.setY(Double.valueOf(y));
        newClick.setR(newHit.getR());

        serviceManager.serviceClick(newClick);
        saveHit(newClick);

        PrimeFaces.current().ajax().addCallbackParam("hitResult", newClick.getResult());
    }

    public void serviceForm() {
        logger.info("Form hit detected!");
        if (serviceManager.serviceForm(newHit)) {
            saveHit(newHit);
        }

        newHit = new Hit();

        logger.info("Now, size of results is: {}", hitList.size());
    }

    public void clear() {
        hitList.clear();
        destroySessionHits();
    }

    public void synchronizeDots() {
        hitList = hitDTO.getSessionEntityList(sessionId).orElse(Collections.emptyList());
        logger.info(String.valueOf(hitList.size()));
        Set<String> jsonHitList = new HashSet<>();
        hitList.forEach(hit -> {
            String jsonHit = hit.jsonHit();
            jsonHitList.add(jsonHit);
        });
        PrimeFaces.current().ajax().addCallbackParam("dotsJSON", JSONUtil.toJSON(jsonHitList));
    }

    private void saveHit(Hit aHit) {
        aHit.setSessionId(sessionId);
        hitList.add(aHit);
        hitDTO.save(aHit);
    }

    @PreDestroy
    private void destroySessionHits() {
        hitDTO.deleteSessionEntityList(sessionId);
    }
}