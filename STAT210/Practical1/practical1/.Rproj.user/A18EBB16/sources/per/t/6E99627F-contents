############### prac1.R ###################
# set the number of significant figures for output
options(digits=3, show.signif.stars=F)
# Read data from data file (acid.txt) and store in R in a data frame (acid.df)
acid.df<-read.table("acid.txt",header=T)
# list names of variables
print(names(acid.df))
# list first 3 lines of dataframe
print(head(acid.df,3))
#######################################
# Plot the data 
plot(fungus ~ acid, data=acid.df)
# Fit a linear model with response variable fungus and predictor acid
acidreg.lm <- lm(fungus ~ acid, data=acid.df)
# Summarise the fitted model.
# Obtain the regression coefficients together with
# their standard deviations, t- and P- values.
print(summary(acidreg.lm))
# Produce the Analysis of Variance table
print(anova(acidreg.lm))
#######################################
# Add the least squares line to the plot
abline(acidreg.lm)
legend(13, 30, legend="fungus = 31.7 - 0.75 acid  ")

#Confidence intervals for regression parameters
print(confint(acidreg.lm, level=0.95))

# Diagnostic plots 
# plot all on one page
par(mfrow=c(2,2))
plot(acidreg.lm, which=1:4)

# test for normally distrubted residuals
print(shapiro.test(acidreg.lm$residuals))

#predict the mean fungal growth and
# the 95% CI when acid conc. = 15
# Note use of new=data.frame to indicate values of X
preds<-predict(acidreg.lm,new=data.frame(acid=15), interval="confidence", level=0.95)
print(preds)



