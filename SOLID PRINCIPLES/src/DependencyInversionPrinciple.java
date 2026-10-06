//High level modules should not depend on low level modules
interface RecommendationStrategy{
    void recommend();
}

class TrendingRecommendation implements RecommendationStrategy{
    @Override
    public void recommend() {
        System.out.println("Recommending the trending videos");
    }
}

class GenreRecommendation implements RecommendationStrategy{
    @Override
    public void recommend() {
        System.out.println("Recommending the video according to the genre");
    }
}

class RecentRecommendation implements RecommendationStrategy{
    @Override
    public void recommend() {
        System.out.println("Recommending the recent videos ");
    }
}

class RecommendationAlgorithm{
    private RecommendationStrategy recommendationStrategy;
    public RecommendationAlgorithm(RecommendationStrategy recommendationStrategy){
        this.recommendationStrategy = recommendationStrategy;
    }

    public void recommend(){
        recommendationStrategy.recommend();
    }
}
public class DependencyInversionPrinciple {
    static void main(String[] args) {
        RecommendationStrategy recommendationStrategy = new RecentRecommendation();
        recommendationStrategy.recommend();
        RecommendationAlgorithm recommendationAlgorithm = new RecommendationAlgorithm(new TrendingRecommendation());
        recommendationAlgorithm.recommend();
    }
}
