package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.entity.User;
import com.example.WebLab3.interfaces.OneToManyDAO;
import com.example.WebLab3.interfaces.ServiceManager;
import com.example.WebLab3.utility.HitServiceManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.kopitubruk.util.json.JSONUtil;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Data
@ManagedBean
@SessionScoped
public class HitProcessor {

    private final ServiceManager<Hit> serviceManager = new HitServiceManager();
    private Hit newHit = new Hit();
    private User user;

    @ManagedProperty(value = "#{userToHitsDAO}")
    private OneToManyDAO<Hit, User> hitDTO;

    @PostConstruct
    private void initialUserSessionId() {
        FacesContext fCtx = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);

        user = new User();
        user.setSessionId(session.getId());
        user.setHitList(new ArrayList<>());

        hitDTO.initOwner(user);
    }

    public void serviceClick() {
        log.info("Click detected!");

        String xCoordinate = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String yCoordinate = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");

        Hit newClick = new Hit();
        newClick.setX(Double.valueOf(xCoordinate));
        newClick.setY(Double.valueOf(yCoordinate));
        newClick.setR(newHit.getR());

        serviceManager.serviceWithoutValidation(newClick);
        saveHit(newClick);

        PrimeFaces.current().ajax().addCallbackParam("hitResult", newClick.getResult());
    }

    public void serviceForm() {
        log.info("Form hit detected!");

        if (serviceManager.serviceWithValidation(newHit)) {
            saveHit(newHit);
        }
        newHit = new Hit();
    }

    public void clear() {
        log.info("User {} deleting Hits!", user.getSessionId());

        user.getHitList().clear();

        newHit.setX(null);
        newHit.setY(null);
        newHit.setR(null);

        hitDTO.deleteOwnerUnits(user);
    }

    public void synchronizeDots() {
        user.setHitList(hitDTO.getOwnerUnitsList(user).orElse(new ArrayList<>()));

        Set<String> jsonHitList = new HashSet<>();
        user.getHitList()
                .stream()
                .map(Hit::jsonHit)
                .forEach(jsonHitList::add);

        PrimeFaces.current().ajax().addCallbackParam("dotsJSON", JSONUtil.toJSON(jsonHitList));
    }

    private void saveHit(Hit aHit) {
        aHit.setUser(user);
        user.getHitList().add(aHit);
        hitDTO.saveUnit(aHit);
    }

    @PreDestroy
    private void destroySessionHits() {
        hitDTO.removeOwner(user);
    }
}