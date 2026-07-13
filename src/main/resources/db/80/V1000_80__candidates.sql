ALTER TABLE `aqua_net_user`
    ADD COLUMN IF NOT EXISTS `display_candidates` bit NOT NULL DEFAULT b'0';