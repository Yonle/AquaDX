<!-- Svelte 4.2.11 -->

<script lang="ts">
  import { slide, fade } from "svelte/transition";
  import type { AquaNetUser } from "../../libs/generalTypes";
  import { CARD, USER } from "../../libs/sdk";
  import StatusOverlays from "../../components/StatusOverlays.svelte";
  import { t, ts } from "../../libs/i18n";
  import ChuniSettings from "../../components/settings/ChuniSettings.svelte";
  import Mai2Settings from "../../components/settings/Mai2Settings.svelte";
  import WaccaSettings from "../../components/settings/WaccaSettings.svelte";
  import GeneralGameSettings from "../../components/settings/GeneralGameSettings.svelte";
  import OngekiSettings from "../../components/settings/OngekiSettings.svelte";
  import UserSettings from "../../components/settings/UserSettings.svelte";
  import type { Component } from "svelte";
  import { EN_REF } from "../../libs/i18n/en_ref";
  import type { GameName } from "../../libs/scoring";

  USER.ensureLoggedIn()

  let me: AquaNetUser;
  let error: string;
  export let page: string = "profile";

  const pages: Record<string, Component> = {
    "profile": UserSettings, "chu3": ChuniSettings,
    "mai2": Mai2Settings, "wacca": WaccaSettings, 
    "ongeki": OngekiSettings, "global": GeneralGameSettings
  }

  if (!pages[page] && page)
    error = t("404", {pathname: new URL(location.href).pathname});

  let userGames: GameName[] = [];
  
  USER.me().then(m => {
    me = m
    CARD.userGames(m.username).then(cards => userGames = Object.keys(cards).filter(k => !!cards[k as GameName]) as GameName[])
  })
    .catch(e => error = e.message)

  
</script>

<main class="content">
  <div class="outer-title-options">
    <h2>{t('settings.title')}</h2>
    <nav>
      {#each Object.entries(pages).filter(v => v[0] == "profile" || v[0] == "global" || userGames.includes(v[0] as GameName)) as tab}
        <a href={`/settings/${tab[0] != "profile" ? tab[0] : ""}`} transition:slide={{axis: 'x'}} 
          class:active={tab[0] == page || (tab[0] == "profile" && !page)} role="button" tabindex="0">
          {ts(`settings.tabs.${tab[0]}`)}
        </a>
      {/each}
    </nav>
  </div>

  <h2 class="header">
    {t('settings.page-title', {page: ts(`settings.tabs.${page}`)})}
  </h2>

  {#if pages[page]}
    <svelte:component this={pages[page]} />
  {/if}
</main>
<StatusOverlays {error} />
<style lang="sass">
  h2.header
    margin: 0 0 0.5rem 0
</style>