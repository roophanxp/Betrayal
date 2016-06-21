package com.jnv.betrayal.dungeon.phases;

import com.jnv.betrayal.dungeon.utils.Panel;

public class Attack extends Phase {

	Attack(final PhaseManager pm) {
		super(pm);
		createPanel("Attack Phase", 50, Panel.full, new Runnable() {
			@Override
			public void run() {
				pm.nextPhase();
			}
		});
	}

	public int getPhaseNum() {
		return PhaseConst.ATTACK;
	}
}