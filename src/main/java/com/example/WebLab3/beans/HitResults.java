package com.example.WebLab3.beans;

import com.example.WebLab3.beans.dto.HitDTO;
import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.entity.User;
import com.example.WebLab3.interfaces.ServiceManagerInterface;
import com.example.WebLab3.utility.ServiceManager;
import lombok.Data;
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
    private final ServiceManagerInterface serviceManager = new ServiceManager();

    private Hit newHit = new Hit();
    private User user;

    @ManagedProperty(value = "#{hitDTO}")
    private HitDTO hitDTO;

    @PostConstruct
    private void initialUserSessionId() {
        FacesContext fCtx = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);

        user = new User();
        user.setSessionId(session.getId());
        user.setHitList(new ArrayList<>());

        hitDTO.initUser(user);
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

        logger.info("Now, size of results is: {}", user.getHitList().size());

        newHit = new Hit();
    }

    public void clear() {
        logger.info("User {} deleting Hits!", user.getSessionId());

        user.getHitList().clear();

        newHit.setX(null);
        newHit.setY(null);
        newHit.setR(null);

        hitDTO.deleteUserHits(user);
    }

    public void synchronizeDots() {
        user.setHitList(hitDTO.getSessionHitList(user).orElse(new ArrayList<>()));

        Set<String> jsonHitList = new HashSet<>();
        user.getHitList().forEach(hit -> {
            String jsonHit = hit.jsonHit();
            jsonHitList.add(jsonHit);
        });

        PrimeFaces.current().ajax().addCallbackParam("dotsJSON", JSONUtil.toJSON(jsonHitList));
    }

    private void saveHit(Hit aHit) {
        aHit.setUser(user);
        user.getHitList().add(aHit);
        hitDTO.saveHit(aHit);
    }

    @PreDestroy
    private void destroySessionHits() {
        hitDTO.removeUser(user);
    }
}