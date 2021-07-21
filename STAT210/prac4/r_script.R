options(digits=6, show.signif.stars = FALSE)
library(lattice)
source("Rfunctions.R")
samara.df <- read.table("samara.txt",header=T)
samaraEDA <- xyplot(Velocity ~ Load|Tree,data=samara.df,layout=c(3,1),
                    panel=function(x,y){
                      panel.xyplot(x,y)
                      panel.lmline(x,y)} )
print(samaraEDA)

# Now to determine which model we use. Model1 = different slopes, different intercepts (the interaction model)
# Model2 = common slopes, different intercepts.
# Model3 = common slopes, common intercepts (group together and use simple linear regression model)

samara.df$Tree<-factor(samara.df$Tree,labels=c("T1","T2","T3"))

model1 <- lm(Velocity~Load*Tree, data=samara.df)
model2 <- lm(Velocity~Load+Tree, data=samara.df)
model3 <- lm(Velocity~Load, data=samara.df)
anova(model1,model2,model3)

# Since our p-values are more than 0.05 they are not significant
# Which means this tool is in favour of choosing model1. If line2's p-value (0.0501) was
# slightly less (e.g 0.04) we would choose model2.

# Now let's use stepwise variable selection.
# We will use forward option
formL <- formula(~1)
formU <- formula(~ Load*Tree, data=samara.df)
start.model <- lm(Velocity ~1, data=samara.df)
stepf.model <- step(start.model,direction="forward",scope=list(lower=formL,upper=formU))
# The output of this shows AIC starting at -120.45 then as you add variables the number goes
# further negative (-174.3) which is what we want with a forward design. When we add tree
# we don't go further negative, so then the step function prints the AIC again but this time
# comparing with Velocity ~ Load, then it checks what happens when it adds the remaining variables.
# Doing this still isn't helpful because the AIC goes up (from -174.27 to -172.2) so this just
# confirms which variables we'll want to use (Load).

# Anova of stepf.model, simple linear regression againt Velocity~Load
anova(stepf.model)

summary(stepf.model)
betaCI(stepf.model)
# Look at prac4 video for analysis of these tables.
# She ends up deciding on the interaction model.
# Note: There can be different results when using forward and backward stepwise models.

