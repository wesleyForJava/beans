package com.wesley.bean.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesley.bean.dto.ApiResponseEntity;
import com.wesley.bean.exception.ErrorCodeEnum;
import com.wesley.bean.exception.LogicException;

/**
 * 测试控制器
 * ========================
 * Created with IntelliJ IDEA.
 * User：恒宇少年
 * Date：2018/1/7
 * Time：下午3:12
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 *
 * @author yuqiyu
 */
@RestController
public class ExceptionController {
    /**
     * 首页方法
     *
     * @return
     */
    @RequestMapping(value = "/indexByException")
    public ApiResponseEntity<String> index() throws LogicException {
        /**
         * 模拟用户不存在
         * 抛出业务逻辑异常
         */
        if (true) {
            throw new LogicException(ErrorCodeEnum.USER_STATUS_FAILD.toString());
        }
        return ApiResponseEntity.<String>builder().data("this is index mapping").build();
    }
}
