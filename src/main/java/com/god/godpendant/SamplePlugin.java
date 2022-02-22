package com.god.godpendant;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("GodPendant")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class SamplePlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"com.god.godpendant.SampleTransformer"};
	}

	@Override
	public String getModContainerClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getSetupClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
