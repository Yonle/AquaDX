<script lang="ts">
  import { fade } from "svelte/transition";
  import { FADE_IN, FADE_OUT } from "../../libs/config";
  import { t, ts } from "../../libs/i18n";
  import useLocalStorage from "../../libs/hooks/useLocalStorage.svelte";
  import RegionSelector from "./RegionSelector.svelte";
  import { USER } from "../../libs/sdk";
  import type { AquaNetUser } from "../../libs/generalTypes";
  import StatusOverlays from "../StatusOverlays.svelte";

  const rounding = useLocalStorage("rounding", true);

  let me: AquaNetUser;
  let submitting = "";
  let error = "";
  let loading = true;

  USER.me().then((m) => {
    me = m;
    loading = false;
  }).catch(e => error = e.message)

  function submit(field: string, value: string) {
    if (submitting) return
    submitting = field

    USER.setting(field, value).then(() => {
    }).catch(e => error = e.message).finally(() => submitting = "")
  }
</script>

{#if !loading}
  <div class="fields">
    <blockquote class="info">
      {ts("settings.siteNotice")}
    </blockquote>
    <div class="field">
      <div class="bool">
        <input id="rounding" type="checkbox" bind:checked={rounding.value}/>
        <label for="rounding">
          <span class="name">{ts(`settings.fields.rounding.name`)}</span>
          <span class="desc">{ts(`settings.fields.rounding.desc`)}</span>
        </label>
      </div>
    </div>
    <div class="field m-t">
      <div class="bool">
          <input id="displayCandidates" type="checkbox" bind:checked={me.displayCandidates}
            on:change={() => submit('displayCandidates', me.displayCandidates.toString())}/>
          <label for="displayCandidates">
            <span class="name">{ts(`settings.fields.displayCandidates.name`)}</span>
            <span class="desc">{ts(`settings.fields.displayCandidates.desc`)}</span>
          </label>
      </div>
    </div>
    <div class="divider"></div>
    <blockquote class="info">
      {ts("settings.regionNotice")}
    </blockquote>
    <RegionSelector/>
  </div>
{/if}

<StatusOverlays {error} {loading} />

<style lang="sass">
  @use "../../vars"

  .fields
    display: flex
    flex-direction: column
    gap: 12px

  .bool
    display: flex
    align-items: center
    gap: 1rem

    label
      display: flex
      flex-direction: column

      .desc
        opacity: 0.6

  .divider
    width: 100%
    height: 0.5px
    background: white
    opacity: 0.2
    margin: 0.4rem 0
</style>
