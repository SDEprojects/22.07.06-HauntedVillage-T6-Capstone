#!/usr/bin/env sh

#     In this example, any third-party library JARs are located in 'lib' and added to the classpath [-classpath option].
#     Remove this "lib/*" entry from the classpath if you're not using any, which results in the following:
#     java -classpath blackjack-1.0.jar com.games.blackjack.client.Main

#     Note that your application JAR stays on the classpath, regardless of whether you're using additional libraries or not.

java -classpath 22.07.06-HauntedVillage-T6-Capstone.jar:"lib/*" com.game.hauntedvillage.app.Main
