package br.ce.wcaquino.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class HojeComDiferencaMatcher extends TypeSafeMatcher<Date> {

	private Integer qntdDias;
	
	public HojeComDiferencaMatcher(Integer qntdDias) {
		this.qntdDias = qntdDias;
	}
	
	public void describeTo(Description description) {
		//TODO
	}

	@Override
	protected boolean matchesSafely(Date data) {
		return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(qntdDias));
	}
}