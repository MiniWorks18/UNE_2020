options(digits=3, show.signif.stars = F)
source("Rfunctions.R")
nutrition <- read.table("NutritionStudy.txt", header=TRUE)
pairs(nutrition[,c(2,4,5,3)], lower.panel=panel.smooth, upper.panel=panel.cor)
nutrition_no_id <- nutrition[c(2,3,4,5)]

# Multicollinearity tests
cor.prob(nutrition_no_id) # Correlation significance

multi_test2 <- lm(Calories~Fat+Fiber+Age, data=nutrition_no_id)
summary(multi_test2)

library(car)
vif(multi_test2)

formL <- formula(~ 1)
formU <- formula(~ Age+ Fat + Fiber)

no.model <- lm(Calories ~ 1, data=nutrition_no_id)

fstep.model <- step(no.model, direction = "forward",
                    scope=list(lower=formL, upper=formU))
summary(fstep.model)


betaCI(fstep.model)


# Question 2

sandwich.df <- read.table("sandwich.txt", header=T)

# Declare Filling as our factor with:
sandwich.df$Filling <- factor(sandwich.df$Filling)

# Print the numerical summary (i.e mean and variance)
tapply(sandwich.df$Ants, sandwich.df$Filling, mean)
tapply(sandwich.df$Ants, sandwich.df$Filling, var)

# Now visualise the data using boxplot.
plot(Ants~Filling, data=sandwich.df)

#Pt2
library(MASS)
boxcox(Ants~Filling, lambda=seq(from=-1, to=1.3, by=0.01), data=sandwich.df)


# Question 3
hbf.df <- read.table("bloodflow.txt", header=T)
attach(hbf.df)
# Scatter plot
plot(BF~AOT, data=hbf.df)

# Polynomial models
mod1 <- lm(BF~AOT)
mod2 <- lm(BF~AOT + I(AOT^2))
mod3 <- lm(BF~AOT + I(AOT^2) + I(AOT^3))
summary(mod1)
summary(mod2)
summary(mod3)
lines(smooth.spline(AOT, predict(mod1)), col="red", lwd=2, lty=1)
lines(smooth.spline(AOT, predict(mod2)), col="green", lwd=2, lty=2)
lines(smooth.spline(AOT, predict(mod3)), col="blue", lwd=2, lty=3)
legend(330, 88, legend = c("mod1: linear","mod2: x^2","mod3: x^3"), col=c("red","green","blue"), lty=c(1,2,3), lwd=3, bty="n", cex=0.9)

# Check the differences between them.
anova(mod1, mod2)
anova(mod2, mod3)
anova(mod3)


# Alternative method. Keep adding terms until a non-significant result (this happens after adding mod3)
#mod.1 <- lm(BF~AOT)
#anova(mod.1)
#mod.2 <- update(mod.1, .~. + I(AOT^2))
#anova(mod.2)
#mod.3 <- update(mod.2, .~. + I(AOT^3))
#anova(mod.3)
#mod.4 <- update(mod.3, .~. + I(AOT^4))
#anova(mod.4)

# Residuals vs fitted and normal qq
par(mfrow=c(2,2))
plot(mod2, which=c(1,2,3,5))

# 95% confidence plot
par(mfrow=c(1,1))
library(ggplot2)
ggplot(data=mod2, aes(x=AOT, y=BF)) + geom_point(pch=17, col="blue", size=2)+
  geom_smooth(method = "lm", formula = y ~ poly(x, 4), col="red", linetype=2)+
  labs(title="Quadratic polynomial with 95% confidence bands")

