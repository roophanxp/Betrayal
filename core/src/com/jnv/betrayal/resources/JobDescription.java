package com.jnv.betrayal.resources;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class JobDescription {
	private static final String WARRIOR_DESCRIPTION = "Warrior:"  +
			"\n (Team Passive) +25% Attack" + "\n (Ability) Strike a target with \n an extra 50% damage";
	private static final String KNIGHT_DESCRIPTION = "Knight:" +
			"\n (Team Passive) +25% Defense" + "\n (Ability) Defends two targets \n and increase Defense"
			+ "\n lasts 2 turns";
	private static final String PRIEST_DESCRIPTION = "Priest:" +
			"\n (Team Passive) +25% Health" + "\n (Ability) Heal/Buff Atk/Buff Def of \n target *effect: +50% of your own\n stat"
			+" (lasts 1 turn)";
	private static final String THIEF_DESCRIPTION = "Thief:" +	"\n (Team Passive) +50% Gold Reward" +
			"\n (Ability) True Damage Strike \n (50% your current attack)" ;

	public static String getWarriorDescription() {
		return WARRIOR_DESCRIPTION;
	}

	public static String getKnightDescription() {
		return KNIGHT_DESCRIPTION;
	}

	public static String getPriestDescription() {
		return PRIEST_DESCRIPTION;
	}

	public static String getThiefDescription() {
		return THIEF_DESCRIPTION;
	}
}
