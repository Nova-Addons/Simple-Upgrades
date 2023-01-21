package xyz.xenondevs.simpleupgrades

import xyz.xenondevs.nova.addon.Addon
import xyz.xenondevs.simpleupgrades.registry.GUIMaterials
import xyz.xenondevs.simpleupgrades.registry.Items
import xyz.xenondevs.simpleupgrades.registry.UpgradeTypes

object SimpleUpgrades : Addon() {
    
    override fun init() {
        Items.init()
        GUIMaterials.init()
        UpgradeTypes.init()
    }
    
}