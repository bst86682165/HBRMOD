package heavenburnsred.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import heavenburnsred.character.MyCharacter;
import heavenburnsred.util.CardStats;

public class Enhancement extends BaseCard {
    public static final String ID = makeID("Enhancement"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    public static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1);//The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Enhancement() {
        super(ID,info); //Pass the required information to the BaseCard constructor.

        setMagic(MAGIC,UPG_MAGIC); //Sets the card's damage and how much it changes when upgraded.

        tags.add(CardTags.STARTER_DEFEND);


    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(magicUpgrade);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        {
            addToBot(new ApplyPowerAction(p,p,new ArtifactPower(p,magicUpgrade),magicUpgrade));
            //（p，未拥有时），拥有时
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Enhancement();
    }
}


