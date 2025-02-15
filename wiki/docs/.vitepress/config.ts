import { defineConfig } from 'vitepress'

export default defineConfig({
    base: '/endshards/',
    title: "Endshards Wiki",
    description: "The official wiki for the Endshards Minecraft mod.",
    head: [['link', { rel: 'icon', href: '/endshards/img/favicon.ico' }]],
    themeConfig: {
        nav: [
            { text: "Home", link: "/" },
            {
                text: "Versions",
                items: [
                    { text: "Endshards 0.3.0 for 1.21.4", link: "/" }
                    //{ text: "0.2.7", link: "https://github.com/amot-dev/endshards/tree/0.2.7/docs/" },
                    ]
                },
            { text: "Modrinth", link: "https://modrinth.com/mod/endshards" },
            { text: "GitHub", link: "https://github.com/amot-dev/endshards" }
            ],
        sidebar: [
            { text: "Home", link: "/" },
            { text: "Quality of Life", link: "/quality_of_life" },
            {
                text: "Progression",
                items: [
                    { text: "Slaying the Dragon", link: "/progression/slaying_the_dragon" },
                    { text: "Back to the Nether", link: "/progression/back_to_the_nether" },
                    { text: "Depths of the Overworld", link: "/progression/depths_of_the_overworld" },
                    { text: "Preparing for the Nightmare", link: "/progression/preparing_for_the_nightmare" }
                    ]
                },
            {
                text: "Materials",
                items: [
                    { text: "Ores", link: "/materials/ores" },
                    { text: "Drops", link: "/materials/drops" },
                    { text: "Infusion", link: "/materials/infusion" }
                    ]
                },
            {
                text: "Equipment",
                items: [
                    { text: "Ender", link: "/equipment/ender" },
                    { text: "Netherite", link: "/equipment/netherite" },
                    { text: "Sculk", link: "/equipment/sculk" },
                    { text: "Summary", link: "/equipment/summary" }
                    ]
                },
            {
                text: "Nightmare Realm",
                items: [
                    { text: "NOT YET IMPLEMENTED" },
                    { text: "Overview", link: "/nightmare_realm/overview" },
                    { text: "Entrances and Exits", link: "/nightmare_realm/entrances_and_exits" },
                    { text: "Layout", link: "/nightmare_realm/layout" },
                    { text: "Gate of Terror", link: "/nightmare_realm/gate_of_terror" },
                    { text: "Terror Guardian", link: "/nightmare_realm/terror_guardian" }
                    ]
                },
            ]
        }
    }
);
