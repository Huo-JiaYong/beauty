package org.yong.beauty.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.yong.beauty.dto.BaseResult;

import com.alibaba.fastjson.JSON;

/**
 *  全局异常处理
 * @author Yong
 *
 */

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		LOG.error("访问：" + request.getRequestURI() + "发生错误，错误信息:" + exception.getMessage());

		try {
			PrintWriter writer = response.getWriter();
			BaseResult<String> result = new BaseResult(false, exception.getMessage());
			writer.write(JSON.toJSONString(result));
			writer.flush();
		} catch (IOException e) {
			LOG.error("exception:", e);
		}

		return null;
	}

}
