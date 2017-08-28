package devin.spittr.web;

import devin.spittr.constant.BaseConst;
import devin.spittr.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author devin
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    @Autowired
    private SpittleService spittleService;

    /**
     * {@link RequestParam} 查询参数
     * <p>
     * 1. defaultValue 必须为 {@link String} 类型的常量值.
     *
     * @param model 1. 实际上是一个 {@link java.util.Map}, 它会传递给视图, 这样数据就能渲染到客户端了。
     *              2. 也可以直接使用 {@link java.util.Map} 代替 {@link Model}
     *              3. 如果不使用 {@link Model}, 直接返回对象或集合时, 返回值会被放到模型中, 视图名称会根据
     *              请求路径推断得出(如 "/spittles", 则视图名称为 spittles).
     * @param max
     * @param count
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model,
                           @RequestParam(value = "max", defaultValue = BaseConst.MAX_LONG_AS_STRING) long max,
                           @RequestParam(value = "count", defaultValue = "20") int count) {
        model.addAttribute("spittleList", spittleService.findSpittles(max, count));
        return "spittles";
    }

    /**
     * {@link PathVariable} 路径参数
     * <p>
     * 1. 占位符必须使用 "{}" 括起来
     * 2. 当 {@link PathVariable} 没有 value 属性时, 它会假设占位符的名称和方法的参数名相同.
     *
     * @param spittleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") Long spittleId, Model model) {
        model.addAttribute("spittle", spittleService.findOne(spittleId));
        return "spittle";
    }
}
