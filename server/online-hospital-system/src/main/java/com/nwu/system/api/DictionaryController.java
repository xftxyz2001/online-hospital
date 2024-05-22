package com.nwu.system.api;


import com.nwu.base.model.Result;
import com.nwu.system.model.po.Dictionary;
import com.nwu.system.service.IDictionaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author author
 * @since 2024-03-03
 */
@RestController
@Slf4j
@Tag(name = "字典接口")
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private IDictionaryService iDictionaryService;

    @GetMapping("/list")
    @Operation(summary = "查询所有字典")
    public List<Dictionary> list() {
        return iDictionaryService.list();
    }

    @GetMapping("/{code}")
    @Operation(summary = "根据code查询所有字典")
    public Result<Dictionary> queryByCode(@PathVariable String code) {
        return iDictionaryService.queryByCode(code);
    }


}
