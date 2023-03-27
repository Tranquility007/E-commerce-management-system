package com.zd.utils;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class Pagers<T> extends PageInfo<T> {
	private int maxpage;
	private int minpage;
	private int showpcount=10;
	
	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getMinpage() {
		return minpage;
	}

	public void setMinpage(int minpage) {
		this.minpage = minpage;
	}

	public int getShowpcount() {
		return showpcount;
	}

	public void setShowpcount(int showpcount) {
		this.showpcount = showpcount;
	}

	public Pagers(List<T> list) {
		super(list);
		// 获取总页数
		Integer pagecount = super.getPages();
		// 计算最大(最小)显示页数
		Integer maxpage = 0;// 当前显示最大页码
		Integer minpage = 0;
		Integer index =super.getPageNum();// 当前第几页
		if (pagecount <= showpcount) {// 当总页数小于等于显示的页数时
			maxpage = pagecount;
			minpage = 1;
		} else {
			Integer buff = showpcount / 2; // 取中间数。maxpage=index+buff
			buff = index + buff;// 显示的最大页（理论）
			if (buff <= showpcount) {
				maxpage = showpcount;
				minpage = 1;
			} else if (buff < pagecount) {
				maxpage = buff;
				minpage = maxpage - showpcount + 1;

			} else if (buff >= pagecount) {
				maxpage = pagecount;
				minpage = maxpage - showpcount + 1;
			}
		}
		this.maxpage = maxpage;
		this.minpage = minpage;
	}
}
