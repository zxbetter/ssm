package devin.spittr.web;

import devin.spittr.dto.Spitter;
import devin.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author devin
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @Autowired
    private SpitterService spitterService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    /**
     * 表单校验
     * <p></p>
     * Spring 3.0 开始提供了对 Java 校验 API 的支持, 需要保证在类路径下包含 Java API 的实现(如 Hibernate Validator).
     * 1. ${@link Valid} 标注的参数要满足校验限制.
     * 2. ${@link Errors} 参数要紧跟在 ${@link Valid} 注解的参数后面.
     *
     * 请求转发/重定向
     * <p></p>
     * 1. {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
     * 能够识别视图名称中的 "redirect:" 或者 "forward:" 前缀.
     *
     * @param spitter
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        spitterService.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable("username") String username, Model model) {
        Spitter spitter = spitterService.findByUsername(username);
        model.addAttribute("spitter", spitter);
        return "profile";
    }
}
