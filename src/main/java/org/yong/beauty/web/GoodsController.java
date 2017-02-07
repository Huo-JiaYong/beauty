package org.yong.beauty.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yong.beauty.dto.BaseResult;
import org.yong.beauty.entity.Goods;
import org.yong.beauty.enums.ResultEnum;
import org.yong.beauty.exception.BizException;
import org.yong.beauty.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsService gService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer offset, Integer limit) {
		offset = offset == null ? 0 : offset;
		limit = limit == null ? 50 : limit;
		List<Goods> list = gService.getGoodsList(offset, limit);
		model.addAttribute("goodslist", list);
		return "goods_list";
	}

	@RequestMapping(value = "/{goodsId}/buy", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public BaseResult<Object> buy(@CookieValue(value = "userPhone", required = false) Long userPhone,
			@Valid Goods goods, BindingResult bindingResult) {
		if (userPhone == null) {
			return new BaseResult<Object>(false, ResultEnum.INVALID_USER.getMsg());
		}
		
        try {
            gService.buyGoods(userPhone, goods.getGoodsId(), false);
        } catch (BizException e) {
            return new BaseResult<Object>(false, e.getMessage());
        } catch (Exception e) {
            return new BaseResult<Object>(false, ResultEnum.INNER_ERROR.getMsg());
        }

		return new BaseResult<Object>(true, null);
	}
}
