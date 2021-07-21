options(digits = 3, show.signif.stars = FALSE)
source('Rfunctions.r')

coral <- read.table('coral.txt',header = TRUE)
attach(coral)

# Plot the data
plot(temperature,bleach)

# Fit a GLM
mod.glm <- glm(bleach~temperature, family=binomial)

# Test model goodness of fit
anova(mod.glm,test="Chisq")
1-pchisq(18.8,df=18)
# Given this pchisq() function produces a p-value of 0.404 (> 0.05) we can state that
# we have a good fit for our data.

# Check regression coefficient significance
summary(mod.glm)
# The coefficients from the summary table show a p-value for temperature of 0.026 (<0.05), which 
# tells us temperature is a useful predictor variable in our model.
# Interpretaion: Water temperature was found to have a significant effect on coral bleaching (p=0.026).

# Calculate confidence interval
betaCI(mod.glm)
# We see a 95% CI of (0.285,2.1), since this does not contain 0, it reinforces our evidence for 
# water temperature being a useful predictor.

# Predict when temperature is 30
predict(mod.glm, newdata = data.frame(temperature=50), type = "response")
# This gives us a predicted probability of 0.694.

# Apply a curve to the previously plotted data
plot(temperature,bleach)
curve(predict(mod.glm,data.frame(temperature=x),type="response"),add=TRUE)
# Here you can see it's very unprobable that bleaching will occure in temperatures below 26, but very 
# probable with temperatures above 32.
points(temperature,mod.glm$fitted,pch=20)
detach(coral)


### Question2

# Access the dataset from a web location
UCLAadm <- read.csv("https://stats.idre.ucla.edu/stat/data/binary.csv")

# View the first six rows of your dataframe so you know what it looks like.
head(...)

# One of the qualitative predictors needs to be declared as a factor
# Convert that variable  to a factor.  
# In the code below you need to replace ... with the name of your dataframe.
# and replace xxx with the name of the variable
UCLAadm$rank <- factor(UCLAadm$rank)
attach(UCLAadm)


# Plot the data - in this case for gre. 
plot(gre,admit, xlab="gre",ylab="Probability of admission")
gre.glm=glm(admit~gre,family=binomial,UCLAadm)
curve(predict(gre.glm,data.frame(gre=x),type="resp"),add=TRUE)
points(gre,fitted(gre.glm),pch=20)

# Now produce a similar plot with gpa as the predictor.

plot(gpa,admit)
gpa.glm = glm(admit~gre,family=binomial,UCLAadm)
curve(predict(gpa.glm,data.frame(gpa=x),type="resp"),add=TRUE)
points(gpa,fitted(gpa.glm),pch=20)

# Now fit a glm with gre, gpa and rank as predictors

adm.glm <- glm(admit~gre+gpa+rank,family = binomial,UCLAadm)

# Produce the anova table of deviance

anova(adm.glm)

# Check goodness-of-fit

1-pchisq(459,df=394)
# Since this p-value is less than 0.05 (0.013), the model is a very good fit, however, we
# will continue with it anyways.

# Produce a summary table of regression coefficients and 95% CIs

summary(adm.glm)
betaCI(adm.glm)
# Inspecting the p-values of the summary table, we see all p-values are below 0.05 which means
# all predictors are useful.
# From the regression conefficients we can calculate their effect on the model for each unit increase while
# holding the other variables constant.
# rank2 is -0.675 units different from rank1, and so
(exp(-0.675)-1)*100
# is equal to -49.1, or rank2 has 49.1% less of an effect than rank1
# This can be repeated for all the estimate coefficients

# Calculate odds ratios and CIs
exp(cbind(OR = coef(adm.glm), confint(adm.glm)))


####################################################################
#### Only if you've got time (or can perhaps explore later ...)
###################################################################
# Set up a data frame containing the values you want for the independent variables
newdata <- data.frame(
  gre = rep(seq(from = 200, to = 800, length.out = 100), 4 * 4),
  gpa = rep(c(2.5, 3, 3.5, 4), each = 100 * 4),
  rank = factor(rep(rep(1:4, each = 100), 4)))

# Predict the probabilities for our input data as well as their standard errors.  
#Remember to adjust the name of the model as appropriate.
newdata[, c("p", "se")] <- predict(a, newdata,
                                   type = "response", se.fit=TRUE)[-3]

# Load ggplot2
library(ggplot2)
# Create four plots, one for each level of gpa we used (2.5, 3, 3.5, 4) with the colour of the lines indicating the rank the predicted probabilities were for.

ggplot(newdata, aes(x = gre, y = p, colour = rank)) +
  geom_line() +
  facet_wrap(~gpa)
