package icu.samnyan.aqua.net.games.ongeki

import ext.API
import ext.returns
import ext.vars
import icu.samnyan.aqua.net.games.IExportClass
import icu.samnyan.aqua.net.games.ImportClass
import icu.samnyan.aqua.net.games.ImportController
import icu.samnyan.aqua.sega.ongeki.model.*
import icu.samnyan.aqua.sega.ongeki.OngekiRepos
import icu.samnyan.aqua.sega.ongeki.OngekiUserLinked
import icu.samnyan.aqua.sega.ongeki.OngekiUserRepos
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.full.declaredMembers

@RestController
@API("api/v2/game/ongeki")
class OngekiImport(
    val repos: OngekiUserRepos,
) : ImportController<OngekiDataExport, UserData>(
    "SDDT", "ongeki", OngekiDataExport::class,
    exportFields = OngekiDataExport::class.vars().associateBy {
        it.name.replace("List", "").lowercase()
    },
    exportRepos = OngekiDataExport::class.vars()
        .filter { f -> f.name !in setOf("gameId", "userData") }
        .associateWith { OngekiUserRepos::class.declaredMembers
            .filter { f -> f returns OngekiUserLinked::class }
            .firstOrNull { f -> f.name == it.name
                || f.name == (it.name.substring(4, 5).lowercase() + it.name.substring(5)).replace("List", "") // strip user
                || f.name == it.name.replace("List", "")
            }
            ?.call(repos) as OngekiUserLinked<*>? ?: error("No matching field found for ${it.name}")
        },
    artemisRenames = mapOf() // TODO (almost nobody uses this so it's very low priority)
) {
    override fun createEmpty() = OngekiDataExport()
    override val userDataRepo = repos.data
}


data class OngekiDataExport(
    override var gameId: String = "SDDT",
    override var userData: UserData,
    var userActivityList: List<UserActivity>,
    var userBossList: List<UserBoss>,
    var userCardList: List<UserCard>,
    var userChapterList: List<UserChapter>,
    var userCharacterList: List<UserCharacter>,
    var userDeckList: List<UserDeck>,
    var userEventMusicList: List<UserEventMusic>,
    var userEventPointList: List<UserEventPoint>,
    var userGeneralDataList: List<UserGeneralData>,
    var userItemList: List<UserItem>,
    var userKopList: List<UserKop>,
    var userLoginBonusList: List<UserLoginBonus>,
    var userMemoryChapterList: List<UserMemoryChapter>,
    var userMissionPointList: List<UserMissionPoint>,
    var userMusicDetailList: List<UserMusicDetail>,
    var userMusicItemList: List<UserMusicItem>,
    var userOption: UserOption,
    var userPlaylogList: List<UserPlaylog>,
    var userRivalList: List<UserRival>,
    var userScenarioList: List<UserScenario>,
    var userStoryList: List<UserStory>,
    var userTechCountList: List<UserTechCount>,
    var userTechEventList: List<UserTechEvent>,
    var userTradeItemList: List<UserTradeItem>,
    var userTrainingRoomList: List<UserTrainingRoom>,
    var userEventMapList: List<UserEventMap>,
    var userSkinList: List<UserSkin>,
    var userRegionsList: List<UserRegions>,
    var userGachaList: List<UserGacha>,
): IExportClass<UserData> {
    constructor() : this("SDDT", UserData(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),ArrayList(), ArrayList(), UserOption(), ArrayList(), ArrayList(),ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList(),ArrayList(), ArrayList(), ArrayList(), ArrayList(), ArrayList())
}
