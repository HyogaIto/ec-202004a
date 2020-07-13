package com.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ヘッダの表示テスト用.
 * 
 * 確認出来たら消してもOKです
 * 
 * @author hiroto.kitamura
 *
 */
@Controller
@RequestMapping("")
public class Test {

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index() {
		return "item_detail";
	}
}
