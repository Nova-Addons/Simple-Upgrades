package xyz.xenondevs.simpleupgrades.registry

import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.simpleupgrades.SimpleUpgrades

@Init
object Items : ItemRegistry by SimpleUpgrades.registry {
    
    val SPEED_UPGRADE = registerItem("speed_upgrade")
    val EFFICIENCY_UPGRADE = registerItem("efficiency_upgrade")
    val ENERGY_UPGRADE = registerItem("energy_upgrade")
    val RANGE_UPGRADE = registerItem("range_upgrade")
    val FLUID_UPGRADE = registerItem("fluid_upgrade")
    
}