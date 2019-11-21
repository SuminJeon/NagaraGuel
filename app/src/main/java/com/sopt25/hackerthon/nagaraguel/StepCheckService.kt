package com.sopt25.hackerthon.nagaraguel

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log

public class StepCheckService : Service(), SensorEventListener {

    var stepCount: Int = 0
    var lastCount: Int = 0
    var lastTime: Long? = 0

    var speed: Float? = null

    var x: Float? = null
    var y: Float? = null
    var z: Float? = null

    var lastX: Float? = 0.0f
    var lastY: Float? = 0.0f
    var lastZ: Float? = 0.0f

    val SHAKE_THRESHOLD: Int = 1200


    private lateinit var sensorManager: SensorManager
    private var accelerometerSensor: Sensor? = null

    override fun onCreate() {
        super.onCreate()
        Log.i("onCreate", "IN")
        // 센서 초기화
        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        this.accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    // 바인드 서비스
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("onStartCommand", "IN")
        if (accelerometerSensor != null) {
            sensorManager.registerListener(
                this,
                accelerometerSensor,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("onDestroy", "IN")
        super.onDestroy()
        if (sensorManager != null) {
            sensorManager.unregisterListener(this)
            stepCount = 0
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        Log.i("onSensorChanged", "IN")
        if (p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            var currentTime: Long = System.currentTimeMillis()
            var gabOfTime: Long = currentTime - lastTime!!

            if (gabOfTime > 100) {
                lastTime = currentTime

                x = p0.values[0]
                y = p0.values[1]
                z = p0.values[2]

                Log.i("x", x.toString())
                Log.i("y", y.toString())
                Log.i("z", z.toString())
                Log.i("lastX1", lastX.toString())
                Log.i("lastY1", lastY.toString())
                Log.i("lastZ1", lastZ.toString())



                speed = Math.abs(x!! + y!! + z!! - lastX!! - lastY!! - lastZ!!) / gabOfTime * 10000
                Log.i("speed", speed.toString())


                if (speed!! > SHAKE_THRESHOLD) {
                    Log.i("onSensorChanged_2", "IN")
                    var intent = Intent("manbo") // this, MainActivity::class.java
                    stepCount = stepCount + 1
                    lastCount = 1000 - stepCount

                    // var msg: String? = (stepCount/2).toString()
                    intent.putExtra("stepService", stepCount)
                    intent.putExtra("lastService", lastCount)

                    Log.i("result", stepCount.toString())


                    sendBroadcast(intent)
                }
                lastX = p0.values[0]
                lastY = p0.values[1]
                lastZ = p0.values[2]

                Log.i("lastX2", lastX.toString())
                Log.i("lastY2", lastY.toString())
                Log.i("lastZ2", lastZ.toString())
            }
        }
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}