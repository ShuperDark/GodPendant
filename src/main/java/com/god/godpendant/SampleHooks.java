package com.god.godpendant;

import net.minecraft.util.DamageSource;

public class SampleHooks {
	public static void NodamageEntity(DamageSource damageSrc, float damageAmount){
		//何もしないので落ちる
        return;
    }
}
