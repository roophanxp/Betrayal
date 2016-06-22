/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class Weapon extends Equip implements DualWieldable {

	private int attack;
	private Texture leftPreview, rightPreview;

	public Weapon(int id, String name, BetrayalAssetManager res, int cost_buy, int new_attack, String description) {
		super(id, name, res, cost_buy, description);
		attack = new_attack;
		itemDescription = description;
	}

	public Weapon(String name, BetrayalAssetManager res) {
		super(name, res);
		leftPreview = res.getTexture(ItemNaming.toPreview(name, true));
		rightPreview = res.getTexture(ItemNaming.toPreview(name, false));
	}

	public int getAttack() {return attack;}

	public Texture getLeftPreview() {
		return leftPreview;
	}

	public Texture getRightPreview() {
		return rightPreview;
	}

	public static class WeaponFactory {

		private int id = -1;
		private String name = "";
		private int costBuy = -1;
		private int attack = -1;
		private BetrayalAssetManager res;
		private String description;
		public WeaponFactory(BetrayalAssetManager res) {
			this.res = res;
		}

		public WeaponFactory id(int id) {
			this.id = id;
			return this;
		}

		public WeaponFactory name(String name) {
			this.name = name;
			return this;
		}

		public WeaponFactory costBuy(int costBuy) {
			this.costBuy = costBuy;
			return this;
		}

		public WeaponFactory attack(int attack) {
			this.attack = attack;
			return this;
		}
		public WeaponFactory description(String description){
			this.description = description;
			return this;
		}

		public Weapon build() {
			return new Weapon(id, name, res, costBuy, attack, description);
		}

	}
}
