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
		x = 0;
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
		x = 0;
		n = 4;
        while (x < level) {
            n = n + 1;
            x = x +2;
            }

        min = 0;
        max = n+1;
    	int dexterity = r.nextInt(max - min) + min;
		weaponstats.setDexterity(dexterity);
	}
	
	public void randomStamina(int level)
	{
		x = 0;
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
		x = 0;
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
		String[] meleefirstname = {"Dusk ","Dark ","Burning ","Bright ","Light ","Frost ","Night ","Gem ","Diamond ","War ",
								   "Infernal ", "Souldraining ", "Phantom ","Fiendish ", "Bone ","Skull ","Abyssal ","Cinder ",
								   "Crystal ","War ","Frozen ", "Rage ","Hunter's ","Sanguine ", "Black ","Blue ","Red ",
								   "Silver ", "Golden ","Titanic ","Wicked ","Ghost ","Soul ","Pain ","Deadly ","Rusty ",
								   "Demon ","Old ","Undead ","Living ","Devouring ","Blazing ","Chrome ","Dragon ","Flame ",
								   "Omni ","Vortex ","Storm ","Void ","Hate ","Arcane ","Last ","Serrated ","Unsealed ", "Rainbowglass "
								   ,"Stardust ","Tempest ","Blaster ", "Doom ","Miracle ","Doom ","Depths ","Bloodgod's ","Demise ",
								   "Eternal ","Hera's ", "Edea's ","Athene's ", "Assault ","Destruction ","Unknown ","Oblivion's ",
								   "Vow's", "Age ","Iceborn ","Thunderborn ","Lightborn ","Stormborn ","Darkborn ","Lightsworn ",
								   "Valkyrie's ","Archfiend's ","Salamandra's ","Starborn ","Shadowmist's", "Toxic ","Darkworld's ",
								   "Keeper's ","Luster ","Ritual ","Necro ","Dust ","Tormentor's ","Buster ","Nova ","Tigerking's ",
								   "Shdowborn ","Forbidden's ","Dragonborn ","Hurrican ","Ancient ","Samurai ","Odin's ","Nordic ",
								   "Solemn ","Envoy's ","Emissary's ","Yamato's ","Arasuda's ","Berserker's ","Mystic ","Armageddon ",
								   "Unleashed ","Amaterasu's ","Odd ","Samsara's ","Soulreaver's ","Constellar ","Cursed ",
								   "Nightking's ","Stormking's ","Judgement ","Blackshield ","Dragonknight's ","Serpentfire ","Nightmare ",
								   "The Prophet's ","Fate ","Deception ","Common ",""};
		
		String[] meleesecondname = {"Blade","Dagger","Sword","Edge","Knife","Scepter","Wand","Hammer","Axe","Twinaxe",
									 "Mallet","Machete ","Maw","Scimitar ","Rod","Dirk","Sabre","Spear","Brutalizer","Sealbreaker",
									 "Mace","Katana","Tachi","Shoto","Two-Hander","Scythe","Lance","Broadsword","Slayer","Kunai",
									 "Bloodthirster"};
		
//		String [] rangedfirstname = {"Nightstalkers Bow","Rangers Bow","Long Bow","Short Bow","Shadowmist Bow","Gem Bow",
//									 "Skull Bow","Snipers Bow","Smiting Bow","Frost Bow","Infernal Bow","Runaans Bow",
//									 "Bone Bow","Light Bow","Dusk Bow","Warbow","Recurve Bow,","Hunters Bow","Rapidfire Bow",
//										 "Giant Arrow Bow","Deathbringer Bow","Worldbreaker Bow","Master Bow","Lighning Bow",
//										 "Thunder Bow", "Lightray Bow","Eruption yBow","Souldraining Bow","Redeemers Bow","Fiendish Bow",
//										 "Deathblossom Bow","Forest keepers Bow","Phantom Bow","Crossbow","Abyss Bow",};
//		Not ready yet!
		
	
		
		String randommeleefirstname = (meleefirstname[new Random().nextInt(meleefirstname.length)]);
		String randommeleesecondname = (meleesecondname[new Random().nextInt(meleesecondname.length)]);
		randomname = randommeleefirstname + randommeleesecondname;
		
	}


	
	
	
}