package com.android.goally.data.model.api.response.reminder

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReminderResponse(
    @SerializedName("routines"   ) var routines   : ArrayList<Routines>   = arrayListOf(),
    @SerializedName("checklists" ) var checklists : ArrayList<Checklists> = arrayListOf()
)

data class Schedule (

    @SerializedName("Sun" ) var Sun : String? = null,
    @SerializedName("Mon" ) var Mon : String? = null,
    @SerializedName("Tue" ) var Tue : String? = null,
    @SerializedName("Wed" ) var Wed : String? = null,
    @SerializedName("Thu" ) var Thu : String? = null,
    @SerializedName("Fri" ) var Fri : String? = null,
    @SerializedName("Sat" ) var Sat : String? = null

): Serializable

data class Activities (

    @SerializedName("name"                ) var name                : String?  = null,
    @SerializedName("minCompletionTime"   ) var minCompletionTime   : Int?     = null,
    @SerializedName("maxCompletionTime"   ) var maxCompletionTime   : Float?     = null,
    @SerializedName("audioUrl"            ) var audioUrl            : String?  = null,
    @SerializedName("ordering"            ) var ordering            : Int?     = null,
    @SerializedName("imgURL"              ) var imgURL              : String?  = null,
    @SerializedName("audioType"           ) var audioType           : String?  = null,
    @SerializedName("createdBy"           ) var createdBy           : String?  = null,
    @SerializedName("parentActivityId"    ) var parentActivityId    : String?  = null,
    @SerializedName("allowCancelActivity" ) var allowCancelActivity : Boolean? = null,
    @SerializedName("allowPauseActivity"  ) var allowPauseActivity  : Boolean? = null,
    @SerializedName("allowPush"           ) var allowPush           : Boolean? = null,
    @SerializedName("allowBack"           ) var allowBack           : Boolean? = null,
    @SerializedName("showTimer"           ) var showTimer           : Boolean? = null,
    @SerializedName("libraryType"         ) var libraryType         : String?  = null,
    @SerializedName("migrated"            ) var migrated            : Boolean? = null,
    @SerializedName("autoComplete"        ) var autoComplete        : Boolean? = null,
    @SerializedName("numOfAudioRepeats"   ) var numOfAudioRepeats   : Int?     = null,
    @SerializedName("allowRestart"        ) var allowRestart        : Boolean? = null,
    @SerializedName("_id"                 ) var Id                  : String?  = null,
    @SerializedName("createdAt"           ) var createdAt           : String?  = null,
    @SerializedName("updatedAt"           ) var updatedAt           : String?  = null

): Serializable

data class RoutineNotifications (

    @SerializedName("name"       ) var name       : String?  = null,
    @SerializedName("isActive"   ) var isActive   : Boolean? = null,
    @SerializedName("audioUrl"   ) var audioUrl   : String?  = null,
    @SerializedName("isReadText" ) var isReadText : Boolean? = null,
    @SerializedName("timeBefore" ) var timeBefore : Int?     = null,
    @SerializedName("_id"        ) var Id         : String?  = null

): Serializable

data class RoutineNotificationsV2 (

    @SerializedName("notificationType" ) var notificationType : String? = null,
    @SerializedName("minutesBefore"    ) var minutesBefore    : Int?    = null,
    @SerializedName("_id"              ) var Id               : String? = null

): Serializable

data class LastSchedule (

    @SerializedName("Sun" ) var Sun : String? = null,
    @SerializedName("Mon" ) var Mon : String? = null,
    @SerializedName("Tue" ) var Tue : String? = null,
    @SerializedName("Wed" ) var Wed : String? = null,
    @SerializedName("Thu" ) var Thu : String? = null,
    @SerializedName("Fri" ) var Fri : String? = null,
    @SerializedName("Sat" ) var Sat : String? = null

): Serializable

data class ScheduleV2 (

    @SerializedName("timeType"              ) var timeType              : String?            = null,
    @SerializedName("type"                  ) var type                  : String?            = null,
    @SerializedName("dailyRepeatValues"     ) var dailyRepeatValues     : DailyRepeatValues? = DailyRepeatValues(),
    @SerializedName("yearlyRepeatDateValue" ) var yearlyRepeatDateValue : String?            = null,
    @SerializedName("timeValue"             ) var timeValue             : String?            = null

): Serializable

data class AudioList (

    @SerializedName("name"       ) var name       : String?  = null,
    @SerializedName("url"        ) var url        : String?  = null,
    @SerializedName("paUrl"      ) var paUrl      : String?  = null,
    @SerializedName("isSelected" ) var isSelected : Boolean? = null,
    @SerializedName("ordering"   ) var ordering   : Int?     = null

): Serializable


data class Routines (

    @SerializedName("enableVacationMode"                   ) var enableVacationMode                   : Boolean?                          = null,
    @SerializedName("_id"                                  ) var Id                                   : String?                           = null,
    @SerializedName("name"                                 ) var name                                 : String?                           = null,
    @SerializedName("rewardType"                           ) var rewardType                           : String?                           = null,
    @SerializedName("appRewards"                           ) var appRewards                           : ArrayList<String>                 = arrayListOf(),
    @SerializedName("numberOfPointsOnTime"                 ) var numberOfPointsOnTime                 : Int?                              = null,
    @SerializedName("numberOfPointsLate"                   ) var numberOfPointsLate                   : Int?                              = null,
    @SerializedName("numberOfPuzzlesOnTime"                ) var numberOfPuzzlesOnTime                : Int?                              = null,
    @SerializedName("numberOfPuzzlesLate"                  ) var numberOfPuzzlesLate                  : Int?                              = null,
    @SerializedName("type"                                 ) var type                                 : String?                           = null,
    @SerializedName("imgURL"                               ) var imgURL                               : String?                           = null,
    @SerializedName("ordering"                             ) var ordering                             : Int?                              = null,
    @SerializedName("parentRoutineId"                      ) var parentRoutineId                      : String?                           = null,
    @SerializedName("createdBy"                            ) var createdBy                            : String?                           = null,
    @SerializedName("clientId"                             ) var clientId                             : String?                           = null,
    @SerializedName("schedule"                             ) var schedule                             : Schedule?                         = Schedule(),
    @SerializedName("libraryType"                          ) var libraryType                          : String?                           = null,
    @SerializedName("activities"                           ) var activities                           : ArrayList<Activities>             = arrayListOf(),
    @SerializedName("migrated"                             ) var migrated                             : Boolean?                          = null,
    @SerializedName("showTimer"                            ) var showTimer                            : Boolean?                          = null,
    @SerializedName("allowClientToCancel"                  ) var allowClientToCancel                  : Boolean?                          = null,
    @SerializedName("allowToOverride"                      ) var allowToOverride                      : Boolean?                          = null,
    @SerializedName("showOnLearnerApp"                     ) var showOnLearnerApp                     : Boolean?                          = null,
    @SerializedName("devices"                              ) var devices                              : ArrayList<String>                 = arrayListOf(),
    @SerializedName("routineNotifications"                 ) var routineNotifications                 : ArrayList<RoutineNotifications>   = arrayListOf(),
    @SerializedName("routineNotificationsV2"               ) var routineNotificationsV2               : ArrayList<RoutineNotificationsV2> = arrayListOf(),
    @SerializedName("notifsV2SoundName"                    ) var notifsV2SoundName                    : String?                           = null,
    @SerializedName("notifsV2SoundUrl"                     ) var notifsV2SoundUrl                     : String?                           = null,
    @SerializedName("category"                             ) var category                             : String?                           = null,
    @SerializedName("enableEmotionalFeedback"              ) var enableEmotionalFeedback              : Boolean?                          = null,
    @SerializedName("entityType"                           ) var entityType                           : String?                           = null,
    @SerializedName("ctaOrdering"                          ) var ctaOrdering                          : Int?                              = null,
    @SerializedName("lastSchedule"                         ) var lastSchedule                         : LastSchedule?                     = LastSchedule(),
    @SerializedName("tags"                                 ) var tags                                 : ArrayList<String>                 = arrayListOf(),
    @SerializedName("allowRecap"                           ) var allowRecap                           : Boolean?                          = null,
    @SerializedName("allowSnooze"                          ) var allowSnooze                          : Boolean?                          = null,
    @SerializedName("numberOfSnoozeAllowed"                ) var numberOfSnoozeAllowed                : Int?                              = null,
    @SerializedName("limitEarlyStart"                      ) var limitEarlyStart                      : Boolean?                          = null,
    @SerializedName("earlyStartMinutes"                    ) var earlyStartMinutes                    : Int?                              = null,
    @SerializedName("allowUpdateNightLightAndNoiseMachine" ) var allowUpdateNightLightAndNoiseMachine : Boolean?                          = null,
    @SerializedName("enableChillZoneWatch"                 ) var enableChillZoneWatch                 : Boolean?                          = null,
    @SerializedName("enableWeatherWatch"                   ) var enableWeatherWatch                   : Boolean?                          = null,
    @SerializedName("templateDisabledClientIds"            ) var templateDisabledClientIds            : ArrayList<String>                 = arrayListOf(),
    @SerializedName("isScheduledByV2"                      ) var isScheduledByV2                      : Boolean?                          = null,
    @SerializedName("scheduleV2"                           ) var scheduleV2                           : ScheduleV2?                       = ScheduleV2(),
    @SerializedName("limitRunPerDay"                       ) var limitRunPerDay                       : Boolean?                          = null,
    @SerializedName("numberOfRunPerDay"                    ) var numberOfRunPerDay                    : Int?                              = null,
    @SerializedName("limitRunPerHour"                      ) var limitRunPerHour                      : Boolean?                          = null,
    @SerializedName("numberOfRunPerHour"                   ) var numberOfRunPerHour                   : Int?                              = null,
    @SerializedName("isCreatedByDevice"                    ) var isCreatedByDevice                    : Boolean?                          = null,
    @SerializedName("audioEvents"                          ) var audioEvents                          : ArrayList<AudioEvents>            = arrayListOf(),
    @SerializedName("enableSpeedMonitoring"                ) var enableSpeedMonitoring                : Boolean?                          = null,
    @SerializedName("requiredRewardApproval"               ) var requiredRewardApproval               : Boolean?                          = null,
    @SerializedName("narration"                            ) var narration                            : Boolean?                          = null,
    @SerializedName("__v"                                  ) var _v                                   : Int?                              = null,
    @SerializedName("createdAt"                            ) var createdAt                            : String?                           = null,
    @SerializedName("updatedAt"                            ) var updatedAt                            : String?                           = null,
    @SerializedName("folder"                               ) var folder                               : String?                           = null,
    @SerializedName("folderId"                             ) var folderId                             : String?                           = null

) : Serializable




data class ChecklistNotifications (

    @SerializedName("name"       ) var name       : String?  = null,
    @SerializedName("isActive"   ) var isActive   : Boolean? = null,
    @SerializedName("audioUrl"   ) var audioUrl   : String?  = null,
    @SerializedName("isReadText" ) var isReadText : Boolean? = null,
    @SerializedName("timeBefore" ) var timeBefore : Int?     = null,
    @SerializedName("_id"        ) var Id         : String?  = null

): Serializable


data class ChecklistNotificationsV2 (

    @SerializedName("notificationType" ) var notificationType : String? = null,
    @SerializedName("minutesBefore"    ) var minutesBefore    : Int?    = null,
    @SerializedName("_id"              ) var Id               : String? = null

): Serializable


data class DailyRepeatValues (

    @SerializedName("Sun" ) var Sun : ArrayList<String> = arrayListOf(),
    @SerializedName("Mon" ) var Mon : ArrayList<String> = arrayListOf(),
    @SerializedName("Thu" ) var Thu : ArrayList<String> = arrayListOf(),
    @SerializedName("Wed" ) var Wed : ArrayList<String> = arrayListOf(),
    @SerializedName("Tue" ) var Tue : ArrayList<String> = arrayListOf(),
    @SerializedName("Fri" ) var Fri : ArrayList<String> = arrayListOf(),
    @SerializedName("Sat" ) var Sat : ArrayList<String> = arrayListOf()

): Serializable


data class AudioEvents (

    @SerializedName("event"     ) var event     : String?              = null,
    @SerializedName("audioList" ) var audioList : ArrayList<AudioList> = arrayListOf()

): Serializable


data class Checklists (

    @SerializedName("_id"                      ) var Id                       : String?                             = null,
    @SerializedName("name"                     ) var name                     : String?                             = null,
    @SerializedName("duration"                 ) var duration                 : Int?                                = null,
    @SerializedName("rewardType"               ) var rewardType               : String?                             = null,
    @SerializedName("appRewards"               ) var appRewards               : ArrayList<String>                   = arrayListOf(),
    @SerializedName("numberOfPointsOnTime"     ) var numberOfPointsOnTime     : Int?                                = null,
    @SerializedName("numberOfPointsLate"       ) var numberOfPointsLate       : Int?                                = null,
    @SerializedName("numberOfPuzzlesOnTime"    ) var numberOfPuzzlesOnTime    : Int?                                = null,
    @SerializedName("numberOfPuzzlesLate"      ) var numberOfPuzzlesLate      : Int?                                = null,
    @SerializedName("type"                     ) var type                     : String?                             = null,
    @SerializedName("schedule"                 ) var schedule                 : Schedule?                           = Schedule(),
    @SerializedName("visualAidUrl"             ) var visualAidUrl             : String?                             = null,
    @SerializedName("activities"               ) var activities               : ArrayList<Activities>               = arrayListOf(),
    @SerializedName("enableAudioAid"           ) var enableAudioAid           : Boolean?                            = null,
    @SerializedName("hideActAfterCom"          ) var hideActAfterCom          : Boolean?                            = null,
    @SerializedName("ordering"                 ) var ordering                 : Int?                                = null,
    @SerializedName("createdBy"                ) var createdBy                : String?                             = null,
    @SerializedName("clientId"                 ) var clientId                 : String?                             = null,
    @SerializedName("libraryType"              ) var libraryType              : String?                             = null,
    @SerializedName("devices"                  ) var devices                  : ArrayList<String>                   = arrayListOf(),
    @SerializedName("checklistNotifications"   ) var checklistNotifications   : ArrayList<ChecklistNotifications>   = arrayListOf(),
    @SerializedName("checklistNotificationsV2" ) var checklistNotificationsV2 : ArrayList<ChecklistNotificationsV2> = arrayListOf(),
    @SerializedName("notifsV2SoundName"        ) var notifsV2SoundName        : String?                             = null,
    @SerializedName("notifsV2SoundUrl"         ) var notifsV2SoundUrl         : String?                             = null,
    @SerializedName("entityType"               ) var entityType               : String?                             = null,
    @SerializedName("ctaOrdering"              ) var ctaOrdering              : Int?                                = null,
    @SerializedName("showOnLearnerApp"         ) var showOnLearnerApp         : Boolean?                            = null,
    @SerializedName("enableEmotionalFeedback"  ) var enableEmotionalFeedback  : Boolean?                            = null,
    @SerializedName("showTimer"                ) var showTimer                : Boolean?                            = null,
    @SerializedName("allowCancel"              ) var allowCancel              : Boolean?                            = null,
    @SerializedName("allowToOverride"          ) var allowToOverride          : Boolean?                            = null,
    @SerializedName("allowSnooze"              ) var allowSnooze              : Boolean?                            = null,
    @SerializedName("numberOfSnoozeAllowed"    ) var numberOfSnoozeAllowed    : Int?                                = null,
    @SerializedName("limitEarlyStart"          ) var limitEarlyStart          : Boolean?                            = null,
    @SerializedName("earlyStartMinutes"        ) var earlyStartMinutes        : Int?                                = null,
    @SerializedName("lastSchedule"             ) var lastSchedule             : LastSchedule?                       = LastSchedule(),
    @SerializedName("isScheduledByV2"          ) var isScheduledByV2          : Boolean?                            = null,
    @SerializedName("scheduleV2"               ) var scheduleV2               : ScheduleV2?                         = ScheduleV2(),
    @SerializedName("limitRunPerDay"           ) var limitRunPerDay           : Boolean?                            = null,
    @SerializedName("numberOfRunPerDay"        ) var numberOfRunPerDay        : Int?                                = null,
    @SerializedName("limitRunPerHour"          ) var limitRunPerHour          : Boolean?                            = null,
    @SerializedName("numberOfRunPerHour"       ) var numberOfRunPerHour       : Int?                                = null,
    @SerializedName("audioEvents"              ) var audioEvents              : ArrayList<AudioEvents>              = arrayListOf(),
    @SerializedName("allowRestart"             ) var allowRestart             : Boolean?                            = null,
    @SerializedName("allowPause"               ) var allowPause               : Boolean?                            = null,
    @SerializedName("allowComplete"            ) var allowComplete            : Boolean?                            = null,
    @SerializedName("minDuration"              ) var minDuration              : Int?                                = null,
    @SerializedName("requiredRewardApproval"   ) var requiredRewardApproval   : Boolean?                            = null,
    @SerializedName("narration"                ) var narration                : Boolean?                            = null,
    @SerializedName("tags"                     ) var tags                     : ArrayList<String>                   = arrayListOf(),
    @SerializedName("enableVacationMode"       ) var enableVacationMode       : Boolean?                            = null,
    @SerializedName("__v"                      ) var _v                       : Int?                                = null,
    @SerializedName("createdAt"                ) var createdAt                : String?                             = null,
    @SerializedName("updatedAt"                ) var updatedAt                : String?                             = null,
    @SerializedName("folder"                   ) var folder                   : String?                             = null,
    @SerializedName("folderId"                 ) var folderId                 : String?                             = null,
    @SerializedName("label"                    ) var label                    : String?                             = null

): Serializable


