options(digits=3, show.signif.stars = F)
source("Rfunctions.R")
geese <- read.table("geese.txt", header = T)
attach(geese)

# Fit all variables into glm (This is also the final model)
mod <- glm(RESPONSE~ALTITUDE+LATERAL, family = binomial)

anova(mod)

summary(mod)

# Creating new altitude points
ALTITUDE<-c(rep(3,5), rep(6,5), rep(9,5), rep(12,5))
# Creating new lateral points
LATERAL<-rep(c(0,10,20,30,40),4)
# Compiling above alititude and lateral variables into a data frame
newx<-data.frame(LATERAL, ALTITUDE)
# Creating prediction of response at point newx based on the final model
preds<-predict(mod,new =newx,type="response")
# Mapping our predictions into a 5x4 matrix
prob<-matrix(preds, ncol=4, byrow=F)
# Naming our matrix rows and columns
colnames(prob)<-c("A3", "A6","A9", "A12")
rownames(prob)<-c("L0","L10","L20","L30","L40")
# Printing our matrix
prob
