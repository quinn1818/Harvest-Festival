package joshie.harvest.quests.town.seeds;

import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.core.achievements.HFAchievements;
import joshie.harvest.npcs.HFNPCs;

@HFQuest("seeds.greenpepper")
public class QuestGreenPepper extends QuestShipping {
    public QuestGreenPepper() {
        super(HFAchievements.greenPepper, HFNPCs.GS_OWNER, Season.AUTUMN, 1000);
    }
}
