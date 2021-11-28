package com.example.WebLab3.beans;

import com.example.WebLab3.beans.dto.HitServiceDTO;
import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.utility.HitService;
import lombok.Data;
import org.kopitubruk.util.json.JSONUtil;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ManagedBean
@SessionScoped
public class HitResults {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Hit newHit = new Hit();
    private List<Hit> hitList = new ArrayList<>();
    private HitService hitService = new HitService();

    //todo Задать бин пользователя и сделать OneToMany отношение с выстрелами
    // включить это поле туда
    private String sessionId;

    @ManagedProperty(value = "#{hitService}")
    private HitServiceDTO hitServiceDTO;

    @PostConstruct
    private void initialSessionId() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
        sessionId = session.getId();
    }

    public void addHit() {
        serviceHit(newHit);
        newHit = new Hit();
    }

    //todo Отменить валидацию на клик
    public void addClick() {
        String x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        logger.info("Click detected!");

        Hit newClick = new Hit();
        newClick.setX(Double.valueOf(x));
        newClick.setY(Double.valueOf(y));
        newClick.setR(newHit.getR());

        serviceHit(newClick);

        PrimeFaces.current().ajax().addCallbackParam("hitResult", newClick.getResult());
    }

    public void serviceHit(Hit hit) {
        logger.info("Hit service started with {}!", hit.toString());
        long begin = System.nanoTime();
        if (hitService.service(hit, begin)) {
            hitList.add(hit);
            hitServiceDTO.save(hit);
        }
        logger.info("Now, size of results is: {}", hitList.size());
    }

    public void clear() {
        hitList.clear();
        newHit = new Hit();
    }

    public void getJsonDots() {
        Set<String> jsonHitList = new HashSet<>();
        hitList.forEach(hit -> {
            String jsonHit = hit.jsonHit();
            jsonHitList.add(jsonHit);
        });
        PrimeFaces.current().ajax().addCallbackParam("dotsJSON", JSONUtil.toJSON(jsonHitList));
    }
}
