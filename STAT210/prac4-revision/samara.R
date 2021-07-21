options(digits=6, show.signif.stars=F)
library(lattice)
source("Rfunctions.R")
samara.df <- read.table("samara.txt",header=T)

samaraEDA <- xyplot(Velocity ~ Load|Tree,data=samara.df,layout=c(3,1),
                    panel=function(x,y){
                      panel.xyplot(x,y)
                      panel.lmline(x,y)} )
print(samaraEDA)

samara.df$Tree <-factor(samara.df$Tree)

attach(samara.df)
mod1 <- lm(Velocity~Load*Tree)
anova(mod1)

mod2 <- lm(Velocity~Load+Tree)
anova(mod2)

mod3 <- lm(Velocity~Load)
anova(mod3)

anova(mod1,mod2,mod3)
# The anova of all 3 models shows the difference from one model to the next, featuring the
# p-value to measure how significant the difference is. So the first line of values
# shows nothing of importannce. But the second line is comparing mod1 with mod2 and showing the difference
# between them. It says (p=0.0501 > 0.05) that the difference is not significant, therefore mod2 is 
# of similar fit and since mod2 is simplier than mod1, we favour mod2. The next line shows the difference
# between mod2 and mod3, mod3 is also not significantly different from mod2 (p=0.38) and so for 
# simplicity, we choose mod3. However, if we had a more sensitive threashold e.i 0.1, we would say there 
# is a significant difference between mod1 and mod2 (p=0.0501 < 0.1) and so we would choose the 
# interaction model because the interaction term would be considered significant.

# step wise regression: forward
formL <- formula(~ 1)
formU <- formula(~ Load*Tree, data=samara.df)
# in forward selection you are going to add terms
# hence start.model is the simplest model (overall mean but no predictors)
start.model <- lm(Velocity ~1,data=samara.df)
stepf.model <- step(start.model,direction="forward",scope=list(lower=formL,upper=formU))

# The first table shows a decrease in AIC after adding Load, but then in the next table
# there is an increase from adding Tree. Therefore the best model is Velocity~Load.

# Using backwards stepwise
start.model <- lm(Velocity~Load*Tree)
stepb.model <- step(start.model,direction="backward",scope=list(lower=formL,uppder=formU))

# Doing a backwards step wise comparison, we see we actually lose information (AIC goes up)
# when we remove the interaction term (-175.4 to -172.2) and so we will instead
# use the interaction model.

# Final model: mod1 (interaction model)
finalmod <- lm(Velocity~Load*Tree)
summary(finalmod)
# The summary table of coefficients shows the estimates for the intercept of Tree1 on line1 (0.541).
# Tree2 and Tree3 show the difference between themselves and Tree1. We see that the intercept
# for Tree2 is estimated to be -0.8 units of velocity in comparison to Tree1.
# Tree3's intercept is estimated to be -0.3 units of velocity in comparison to Tree1.
# The interaction terms are the regression lines and how much they differ from the line of Tree1.
# We see that Tree2's regression (3.7, p=0.02) is very different to Tree1, but Tree3 is not
# so different (0.8, p=0.7). We can see this in the plot. Tree1 and Tree3 are nearly the same regression 
# with similar intercepts, but Tree2 is much different.

anova(finalmod)

betaCI(finalmod)
# This table just confirms what we said about the summary table. We can be 95% confident
# Tree2's intercept is between -1.5 and -0.15 compared to Tree1. And Tree2's regression
# increases between 0.6 and 6.8 for each integer increase in load.
