ALTER TABLE `aqua_net_user`
    ADD COLUMN IF NOT EXISTS `hide_country` bit NOT NULL DEFAULT b'0';