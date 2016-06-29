package com.wangxuqin.weatherforecast.entity;

import java.util.List;

public class Data{

    /**
     * result : {"sk":{"wind_strength":"1级","time":"16:06","humidity":"46%","wind_direction":"东风","temp":"31"},"future":[{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160605","week":"星期日","temperature":"22℃~32℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160606","week":"星期一","temperature":"23℃~34℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160607","week":"星期二","temperature":"24℃~33℃"},{"wind":"南风微风","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"date":"20160608","week":"星期三","temperature":"24℃~34℃"},{"wind":"西北风微风","weather":"中雨","weather_id":{"fa":"08","fb":"08"},"date":"20160609","week":"星期四","temperature":"24℃~30℃"},{"wind":"南风微风","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"date":"20160610","week":"星期五","temperature":"24℃~34℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160611","week":"星期六","temperature":"24℃~33℃"}],"today":{"wind":"南风微风","uv_index":"很强","travel_index":"较适宜","temperature":"22℃~32℃","city":"长沙","comfort_index":"","date_y":"2016年06月05日","wash_index":"较适宜","exercise_index":"较适宜","weather":"晴","drying_index":"","weather_id":{"fa":"00","fb":"00"},"dressing_advice":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","week":"星期日","dressing_index":"炎热"}}
     * reason : successed!
     * error_code : 0
     * resultcode : 200
     */
    private ResultEntity result;
    private String reason;
    private int error_code;
    private String resultcode;

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public ResultEntity getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public class ResultEntity {
        /**
         * sk : {"wind_strength":"1级","time":"16:06","humidity":"46%","wind_direction":"东风","temp":"31"}
         * future : [{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160605","week":"星期日","temperature":"22℃~32℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160606","week":"星期一","temperature":"23℃~34℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160607","week":"星期二","temperature":"24℃~33℃"},{"wind":"南风微风","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"date":"20160608","week":"星期三","temperature":"24℃~34℃"},{"wind":"西北风微风","weather":"中雨","weather_id":{"fa":"08","fb":"08"},"date":"20160609","week":"星期四","temperature":"24℃~30℃"},{"wind":"南风微风","weather":"多云转雷阵雨","weather_id":{"fa":"01","fb":"04"},"date":"20160610","week":"星期五","temperature":"24℃~34℃"},{"wind":"南风微风","weather":"晴","weather_id":{"fa":"00","fb":"00"},"date":"20160611","week":"星期六","temperature":"24℃~33℃"}]
         * today : {"wind":"南风微风","uv_index":"很强","travel_index":"较适宜","temperature":"22℃~32℃","city":"长沙","comfort_index":"","date_y":"2016年06月05日","wash_index":"较适宜","exercise_index":"较适宜","weather":"晴","drying_index":"","weather_id":{"fa":"00","fb":"00"},"dressing_advice":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。","week":"星期日","dressing_index":"炎热"}
         */
        private SkEntity sk;
        private List<FutureEntity> future;
        private TodayEntity today;

        public void setSk(SkEntity sk) {
            this.sk = sk;
        }

        public void setFuture(List<FutureEntity> future) {
            this.future = future;
        }

        public void setToday(TodayEntity today) {
            this.today = today;
        }

        public SkEntity getSk() {
            return sk;
        }

        public List<FutureEntity> getFuture() {
            return future;
        }

        public TodayEntity getToday() {
            return today;
        }

        public class SkEntity {
            /**
             * wind_strength : 1级
             * time : 16:06
             * humidity : 46%
             * wind_direction : 东风
             * temp : 31
             */
            private String wind_strength;
            private String time;
            private String humidity;
            private String wind_direction;
            private String temp;

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public String getTime() {
                return time;
            }

            public String getHumidity() {
                return humidity;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public String getTemp() {
                return temp;
            }
        }

        public class FutureEntity {
            /**
             * wind : 南风微风
             * weather : 晴
             * weather_id : {"fa":"00","fb":"00"}
             * date : 20160605
             * week : 星期日
             * temperature : 22℃~32℃
             */
            private String wind;
            private String weather;
            private Weather_idEntity weather_id;
            private String date;
            private String week;
            private String temperature;

            public void setWind(String wind) {
                this.wind = wind;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public void setWeather_id(Weather_idEntity weather_id) {
                this.weather_id = weather_id;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWind() {
                return wind;
            }

            public String getWeather() {
                return weather;
            }

            public Weather_idEntity getWeather_id() {
                return weather_id;
            }

            public String getDate() {
                return date;
            }

            public String getWeek() {
                return week;
            }

            public String getTemperature() {
                return temperature;
            }

            public class Weather_idEntity {
                /**
                 * fa : 00
                 * fb : 00
                 */
                private String fa;
                private String fb;

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }

                public String getFa() {
                    return fa;
                }

                public String getFb() {
                    return fb;
                }
            }
        }

        public class TodayEntity {
            /**
             * wind : 南风微风
             * uv_index : 很强
             * travel_index : 较适宜
             * temperature : 22℃~32℃
             * city : 长沙
             * comfort_index :
             * date_y : 2016年06月05日
             * wash_index : 较适宜
             * exercise_index : 较适宜
             * weather : 晴
             * drying_index :
             * weather_id : {"fa":"00","fb":"00"}
             * dressing_advice : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             * week : 星期日
             * dressing_index : 炎热
             */
            private String wind;
            private String uv_index;
            private String travel_index;
            private String temperature;
            private String city;
            private String comfort_index;
            private String date_y;
            private String wash_index;
            private String exercise_index;
            private String weather;
            private String drying_index;
            private Weather_idEntity weather_id;
            private String dressing_advice;
            private String week;
            private String dressing_index;

            public void setWind(String wind) {
                this.wind = wind;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public void setWeather_id(Weather_idEntity weather_id) {
                this.weather_id = weather_id;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getWind() {
                return wind;
            }

            public String getUv_index() {
                return uv_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public String getTemperature() {
                return temperature;
            }

            public String getCity() {
                return city;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public String getDate_y() {
                return date_y;
            }

            public String getWash_index() {
                return wash_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public String getWeather() {
                return weather;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public Weather_idEntity getWeather_id() {
                return weather_id;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public String getWeek() {
                return week;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public class Weather_idEntity {
                /**
                 * fa : 00
                 * fb : 00
                 */
                private String fa;
                private String fb;

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }

                public String getFa() {
                    return fa;
                }

                public String getFb() {
                    return fb;
                }
            }
        }
    }
}