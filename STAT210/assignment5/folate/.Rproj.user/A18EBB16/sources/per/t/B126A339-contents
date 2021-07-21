options(digits=3, show.signif.stars = F)
source("Rfunctions.r")

folate <- read.table('folate.txt', header = TRUE)
folate$ventilation <- factor(folate$ventilation)

plot(folate~ventilation, data=folate) # Boxplot

mod <- lm(folate~ventilation, data=folate)


anova(mod)
summary(mod)