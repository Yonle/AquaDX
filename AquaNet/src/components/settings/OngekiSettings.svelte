<script lang="ts">
  import { fade } from "svelte/transition";
  import { FADE_IN, FADE_OUT } from "../../libs/config";
  import GameSettingFields from "./GameSettingFields.svelte";
  import { GAME, SETTING, USER } from "../../libs/sdk";
  import { download } from "../../libs/ui";
  import InputField from "../ui/InputField.svelte";
  import Icon from "@iconify/svelte";
  import { t } from "../../libs/i18n";
  import StatusOverlays from "../StatusOverlays.svelte";

  let userNameField: any

  let submitting = "";
  let error: string
  let loading = true;

  USER.me().then(me =>
    GAME.userSummary(me.username, 'ongeki').then(async ({name}) => {
      userNameField = {key: "gameUsername", value: name, type: "String"}
      loading = false;
    }))
  

  function exportData() {
      submitting = "export"
      GAME.export('ongeki')
        .then(data => download(JSON.stringify(data), `AquaDX_ongeki_export_${userNameField.value}.json`))
        .catch(e => error = e.message)
        .finally(() => submitting = "")
    }
</script>

{#if !loading}
  <div>
    <div class="fields">
      <InputField bind:field={userNameField}
        callback={() => SETTING.detailSet('ongeki', 'userName', userNameField.value)}/>
      <GameSettingFields game="ongeki"/>
      <button class="exportButton" on:click={exportData}>
        <Icon icon="bxs:file-export"/>
        {t('settings.export')}
      </button>
    </div>
  </div>
{/if}

<StatusOverlays {error} loading={!userNameField || !!submitting || loading}/>

<style lang="sass">
  .fields
    display: flex
    flex-direction: column
    gap: 12px
</style>