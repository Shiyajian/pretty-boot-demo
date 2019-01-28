package github.shiyajian.pretty.controller;

import github.shiyajian.pretty.commons.Enumerable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 枚举字典控制器
 * @author shiyajian
 * create: 2019-01-27
 */
@RestController
@RequestMapping("/dict")
public class DictionaryController {

    /**
     * 所有需要枚举类的地方，调用此接口，返回整个枚举类列表
     * @param key 枚举类对应的key
     * @return 枚举属性集合
     */
    @GetMapping("/{key}")
    List<Enumerable> getEnums(@PathVariable String key) {
        return null;
    }
}
