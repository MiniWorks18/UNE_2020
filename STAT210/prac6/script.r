library(effects)
options(digits = 3, show.signif.stars = FALSE)
#herbs <- read.table("herbs.txt", header=T)
#attach(herbs)

#herbs$location <- factor(herbs$location)
#herbs$yield <- factor(herbs$yield)
#detach(herbs)

herbicide <- rep(c(1:3),4)
location <- factor(c(rep(1,3),rep(2,3),rep(3,3),rep(4,3)))
yield<-c(12.7,15.2,12.3, 13,16.2,9.4, 15.6, 13.7, 9.1, 7.1, 7.8, 4.7)
herbf<-factor(herbicide)

mod<-lm(yield~herbf)
CRD.eff <- allEffects(mod)
plot(CRD.eff)

anova(mod)
summary(mod)

finalmod <- lm(yield~location+herbf)
RCB.eff <- allEffects(finalmod)
plot(RCB.eff)

anova(finalmod)
summary(finalmod)

# Relevel
reherbf <- relevel(herbf, ref="2")
summary(lm(yield~location+reherbf))
