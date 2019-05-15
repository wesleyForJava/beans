package com.wesley.bean.pattern.adapter;

public class PowerAdapter extends AC220 implements DC5{

	@Override
	public int outputDc5() {
		int adapterInput = super.outputAC220v();
		//变压器...
		int adapterOutput = adapterInput/44;
		System.out.println("使用 PowerAdapter 输入 AC:"+adapterInput+"V"+"输出DC:"+adapterOutput+"V");
		return adapterOutput;
	}

}
