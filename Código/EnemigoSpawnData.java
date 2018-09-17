public class EnemigoSpawnData{

	private int maxHP, atk, def, eExp;
	
	public EnemigoSpawnData(int maxHP, int atk, int def, int eExp){
		this.maxHP = maxHP;
		this.atk = atk;
		this.def = def;
		this.eExp = eExp;
	}

	public int getMaxHP(){ return maxHP;
	}

	public int getAtk(){ return atk;
	}

	public int getDef(){ return def;
	}

	public int geteExp(){ return eExp;
	}
}