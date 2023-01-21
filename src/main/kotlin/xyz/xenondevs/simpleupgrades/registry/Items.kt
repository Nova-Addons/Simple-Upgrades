package xyz.xenondevs.simpleupgrades.registry

import xyz.xenondevs.nova.material.NovaMaterialRegistry.registerItem
import xyz.xenondevs.simpleupgrades.SimpleUpgrades

object Items {
    
    val SPEED_UPGRADE = registerItem(SimpleUpgrades, "speed_upgrade")
    val EFFICIENCY_UPGRADE = registerItem(SimpleUpgrades, "efficiency_upgrade")
    val ENERGY_UPGRADE = registerItem(SimpleUpgrades, "energy_upgrade")
    val RANGE_UPGRADE = registerItem(SimpleUpgrades, "range_upgrade")
    val FLUID_UPGRADE = registerItem(SimpleUpgrades, "fluid_upgrade")
    
    fun init() = Unit
    
}