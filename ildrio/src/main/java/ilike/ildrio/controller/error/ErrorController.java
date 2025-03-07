package ilike.ildrio.controller.error;

import ilike.ildrio.common.SessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@Log4j2
public class ErrorController {


    private final SessionUtil session;

    public ErrorController(SessionUtil session) {
        this.session = session;
    }

   
}
