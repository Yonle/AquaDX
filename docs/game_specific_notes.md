# Game specific notes

## Chunithm (Chusan)

### Required patches
* None

<sub>※ No encryption & No TLS is required if you do not have encryption keys set for your game version (supported on public instance)</sub>

### Additional notes
* Class/Dan, National Matching and LINKED VERSE modes will work after playing the first game  
    (both when you first set up the game and when you update the game's rom or options).
* National Matching is supported out of the box. Services provided by [Yukiotoko](https://gitea.tendokyu.moe/CharaLol/Yukiotoko)
* For user box and LINKED VERSE customization, use the AquaNet website.
* All LINKED VERSE gates are available by default and can be played sequentially. Original condition flags are disregarded due to implementation complexity (and low overall interest).
* Many aspects of the game may not work in freeplay mode, this is not a server-side restriction.

## Maimai DX

### Required patches
* No certificate pinning

<sub>※ No encryption & No TLS is required if you do not have encryption keys set for your game version (supported on public instance)</sub>

### Additional notes
* KOP related features and tournament mode do not work
* All KALEID×SCOPE gates are intended to be open but may be broken

## O.N.G.E.K.I

### Required patches
* No certificate pinning

<sub>※ No encryption & No TLS is required if you do not have encryption keys set for your game version (supported on public instance)</sub>

### Non-working features
* KOP related
* Physical cards

## Card Maker

### Required patches
* No TLS & Encryption (Not implemented as it has three (four?) sets of keys across several games, not worth the effort)

### Additional notes
* Server does not consider gacha rarity and probability weight during card draw.
* Server returns same hard-coded serial for each cards. This is intentional behavior.
* Due to its high correlation with every game endpoints, this may cease to work after major game version up.
* Not priority and may be out of date, broken or even removed in the future due to low overall usage (by player count on public instance)
