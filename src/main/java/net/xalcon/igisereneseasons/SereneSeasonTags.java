package net.xalcon.igisereneseasons;

import com.github.lunatrius.ingameinfo.tag.Tag;
import com.github.lunatrius.ingameinfo.tag.registry.TagRegistry;
import sereneseasons.api.season.ISeasonState;
import sereneseasons.api.season.SeasonHelper;

import java.util.function.Function;

public final class SereneSeasonTags
{
    private abstract static class TagSereneSeasons extends Tag
    {
        @Override
        public String getCategory()
        {
            return "sereneseasons";
        }
    }

    private static <T> void registerTag(String name, Function<ISeasonState, T> callback)
    {
        TagRegistry.INSTANCE.register(new TagSereneSeasons()
        {
            @Override
            public String getValue()
            {
                try
                {
                    return String.valueOf(callback.apply(SeasonHelper.dataProvider.getClientSeasonState()));
                }
                catch(Exception ex)
                {
                    IGISereneSeasons.logger.error(ex);
                    return "ERROR";
                }
            }
        }.setName(name));
    }

    public static void register()
    {
        registerTag("sereneseasonsdayduration", ISeasonState::getDayDuration);
        registerTag("sereneseasonssubseasonduration", ISeasonState::getSubSeasonDuration);
        registerTag("sereneseasonsseasonduration", ISeasonState::getSeasonDuration);
        registerTag("sereneseasonscycleduration", ISeasonState::getCycleDuration);
        registerTag("sereneseasonsseasoncycleticks", ISeasonState::getSeasonCycleTicks);
        registerTag("sereneseasonsday", ISeasonState::getDay);
        registerTag("sereneseasonscurrentseason", s -> s.getSeason().name());
        registerTag("sereneseasonscurrentsubseason", s -> s.getSubSeason().name());
        registerTag("sereneseasonscurrenttropicalseason", ISeasonState::getDay);

        registerTag("sereneseasonscurrentseasonord", s -> s.getSeason().ordinal());
        registerTag("sereneseasonscurrentsubseasonord", s -> s.getSubSeason().ordinal());
        registerTag("sereneseasonsdayofseason", s -> s.getDay() % (s.getSeasonDuration() / s.getDayDuration()));
    }
}
