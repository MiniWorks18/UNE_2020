options(digits=3, show.signif.stars = FALSE)

players <- read.table('players.txt',header = T)


attach(players)
mod1 <- lm(days ~ age)
plot(mod1)
plot(days~age)
abline(mod1)

summary(mod1)
