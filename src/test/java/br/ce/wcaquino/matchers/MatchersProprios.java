package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {

	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher caiNumaSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
	
	public static HojeComDiferencaMatcher ehHojeComDiferencaDias(Integer dias) {
		return new HojeComDiferencaMatcher(dias);
	}
	
	public static HojeComDiferencaMatcher ehHoje() {
		return new HojeComDiferencaMatcher(0);
	}
}
