options(digits=3,show.signif.stars=F) 
source("Rfunctions.R")

# Read data in
heart.df <- read.table("heart-attack.txt",header=TRUE)
attach(heart.df)

# Calculate proportion of people who suffered heart attacks
prop <- attack/total
# Visualisation of data
plot(CK,prop,ylab="Proportion that suffered a heart attack",xlab="level of CK")

# GLM
g=glm(prop~CK, family= binomial, weights = total)
anova(g,test='Chisq')

# Calculate p-value after applying CK
pchisq(df=11, q=28.1,lower.tail = F)

summary(g)

# ln((3/4)/(1-3/4)) = ln(3) = 1.0986
# beta0 + beta1X = ln(3) = 1.0986
# x = (ln(3)-beta0)/beta1
# x = (ln(3)+3.02836)/0.0351 = 117.58

