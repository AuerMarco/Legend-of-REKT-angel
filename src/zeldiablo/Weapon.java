/**
 * @author Wedenig Manuel
 */
package zeldiablo;

import java.util.Random;

public class Weapon {

    private String name;
    private int damage;
    private int x;
    private int min;
    private int max;
    private int n;
    private int y;
    private Random r;
    private Stats weaponstats;
    private String randomname;

    public Weapon(int level)
    {
    	randomName();					
    	name = randomname;
        damage =  0;
        n=0;
        x=0;
        y=2;
        r= new Random();		
        weaponstats = new Stats();
        randomDamage(level);
		randomAttack(level);
		randomDexterity(level);
		randomStamina(level);
		randomDefence(level);
		


    }
    public Weapon(String name, int damage, double attack, double dexterity, double stamina, double defence)
    {

        this.name = name;
        this.damage = damage;
        weaponstats = new Stats();
        weaponstats.setAttack(attack);
        weaponstats.setDexterity(dexterity);
        weaponstats.setStamina(stamina);
        weaponstats.setDefence(defence);
    }

    public String getName()
    {
        return name;
    }
    public int getDamage()
    {
        return damage;
    }
    public void setName(String newName)
    {
        name = newName;
    }
    public void setDamage(int value)
    {
        damage = value;
    }

    public Stats getStats()
    {
        return weaponstats;
    }


    public void randomDamage(int level)
    {

        while(x<level)
        {
            n = n + 10;
            x++;
        }

        min = n / 2;
        max = n+1;
        damage = r.nextInt(max-min) + min;

    }
	
	public void randomAttack(int level)
	{
            n=0;
        x=0;
		n = 4;
        while (x < level) {
            n = n + 1;
            x++;
        }

        min = 0;
        max = n+1;
    	int attack = r.nextInt(max - min) + min;
		weaponstats.setAttack(attack);
    }
	
	public void randomDexterity(int level)
	{
            n=0;
        x=0;
		n = 4;
        while (x < level) {
            n = n + 1;
            x = x +y;
            }

        min = 0;
        max = n+1;
    	int dexterity = r.nextInt(max - min) + min;
		weaponstats.setDexterity(dexterity);
	}
	
	public void randomStamina(int level)
	{
            n=0;
        x=0;
		n = 4;
        while (x < level) {
            n = n + 1;
            x++;
        }

        min = 0;
        max = n;
    	int stamina = r.nextInt(max - min) + min;
		weaponstats.setStamina(stamina);
    }
	
	public void randomDefence(int level)
	{	
            n=0;
        x=0;
		n = 4;
        while (x < level) {
            n = n + 1;
            x++;
        }

        min = 0;
        max = n;
    	int  defence = r.nextInt(max - min) + min;
		weaponstats.setDefence(defence);
    }
	
	public void randomName () 
	{
		String[] meleefirstname = {"Edge ","Hammer ","Rod ","Mace ","Windblade ",
							 	   "Spear ","Battlerod ","Scepter ","Katana ",
							 	   "Axe ","Twinaxe ","Dagger ","Tachi ","Shortaxe ",
							 	   "Stormblade ","Chaos Sword ","Deathblade ","Frost Scepter ",
							 	   "Dusk Dagger ","Stormdagger ","Void Edge ","Double Edged Sword ",
							 	   "Rod of destruction ","Tempest Dagger ","Executioners Axe ",
							 	   "Longsword ","Heavy Axe ","Needlessly big Spear ","Warlords Hammer ",
							 	   "Deaths Dagger ","Blooddraining Sword ","Shadowmist Blade ",
							 	   "Thousand Sword ","Razorshiv ","Collosal Blade ","Galaxy Dusk Blade ",
							 	   "Tormentors Blade ","Bloodthirsters Rod ","Spiritblade Knife ",
							 	   "Bloodmoons Longsword ","Butchers Knife ","Monoliths Sword ",
							 	   "Dragonslayers Scepter ","Phantomsblade ","Lightborns Dagger ","Frostborns Edge ",
							 	   "Darkborn Axe ","Stormborn Knive ","Thunderborn Broadsword ","Stardust Scepter ",
							 	   "Thunderkings Battleblade ","Stareaters Maw ","Infernal Edge ","Nightmare blade ",
							 	   "Elementalists Scepter ","Ashuras Nekroblade ","Constellar Axe ","Kyles Deathblade ",
							 	   "Eccentric Dusk Katana ","Assassins hidden Blade ","Fiendish Edge ","Datk Repulsor "};
		// Noch erweiterbar!!!!
		
		String [] rangedfirstname = {"Nightstalkers Bow","Rangers Bow","Long Bow","Short Bow","Shadowmist Bow","Gem Bow",
									 "Skull Bow","Snipers Bow","Smiting Bow","Frost Bow","Infernal Bow","Runaans Bow",
									 "Bone Bow","Light Bow","Dusk Bow","Warbow","Recurve Bow,","Hunters Bow","Rapidfire Bow",
									 "Giant Arrow Bow","Deathbringer Bow","Worldbreaker Bow","Master Bow","Lighning Bow",
									 "Thunder Bow", "Lightray Bow","Eruption Bow","Souldraining Bow","Redeemers Bow","Fiendish Bow",
									 "Deathblossom Bow","Forest keepers Bow","Phantom Bow","Crossbow","Abyss Bow",};
	
		
		
		String[] secondname      = {"of doom","from the depths","from an other world","from a different dimension",
									"of pain","of miracles","of demise","of eternity","of promises","of the Legend",
									"of the rebellions Rage","of Dark Illusions","of thousand Assaults","from a distant Star",
									"from a distand Gelaxy","from a distand World","from an unknown country",
									"from a distorted World","from the Beyond","from the burning abyss","from a Land without hope",
									"from the realm of the death","of lost Souls"};
		
		String randommeleefirstname = (meleefirstname[new Random().nextInt(meleefirstname.length)]);
		String randommeleesecondname = (secondname[new Random().nextInt(secondname.length)]);
		randomname = randommeleefirstname + randommeleesecondname;
		
	}


	
	
	
}
