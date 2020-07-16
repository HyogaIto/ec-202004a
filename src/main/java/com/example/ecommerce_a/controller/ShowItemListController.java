package com.example.ecommerce_a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce_a.domain.Item;
import com.example.ecommerce_a.form.OrderSelectForm;
import com.example.ecommerce_a.service.ShowItemListService;

/**
 * 商品一覧表示と検索を行うコントローラクラス.
 * 
 * @author junpei.azuma
 *
 */
@Controller
@RequestMapping({"showItemList",""})
public class ShowItemListController {

	@Autowired
	private ShowItemListService service;
	
	@ModelAttribute
	public OrderSelectForm setupOrderSelectForm() {
		return new OrderSelectForm();
	}

	/**
	 * 商品一覧表示と検索を行う.
	 * 
	 * @param name  検索ワード
	 * @param order 表示順(デフォルトでは価格の安い順になるように設定)
	 * @param model リクエストスコープに値を格納するためのオブジェクト
	 * @return 商品一覧ページ
	 */
	@RequestMapping("")
	public String showItemList(String name,OrderSelectForm orderform ,Model model) {
		String order = orderform.getOrder();
		int itemhitSize = service.getItemHitSize(name);
		List<List<Item>> itemList = service.show3colItemList(name, order);

		if (itemhitSize == 0) {
			model.addAttribute("message", "該当する商品がありません");
			itemList = service.show3colItemList("", order);
		}
		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder itemListForAutocomplete = service.getItemListForAutocomplete(service.showItemList(name, order));
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);

		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}
}
