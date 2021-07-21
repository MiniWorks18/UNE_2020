options(digits=3, show.signif.stars = F)
source("Rfunctions.r")
library(effects)

# Read into table
barley <- read.table('Barley.txt',header=TRUE)

# Declaring factors
barley$BSPACE <- factor(barley$BSPACE)
barley$BVARIETY <- factor(barley$BVARIETY)
barley$BBLOCK <- factor(barley$BBLOCK)
 
# Interaction plot
interaction.plot(barley$BSPACE, barley$BVARIETY , barley$BYIELD, fixed=TRUE, leg.bty = "o", lwd=2, ylab="Barley Yield", xlab="Barley Space", trace.label = "Barley Variety")

# Declaring the model
fullmod <- lm(BYIELD~BBLOCK+BSPACE*BVARIETY, data = barley)

# Generating the effects display
all.eff<-allEffects(fullmod)

# Showing the effects display
plot(all.eff)

# Anova of model
anova(fullmod)

# Table of means
print(all.eff)

# Check model assumptions
par(mfrow=c(2,2))
plot(fullmod, which=1:4)
shapiro.test(fullmod$residuals)

# Check model usfulness
summary(fullmod)

# betaCI
betaCI(fullmod)
