# Generated by Django 4.1.7 on 2023-03-18 16:59

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Complaints',
            fields=[
                ('complaint_id', models.AutoField(auto_created=True, primary_key=True, serialize=False)),
                ('img', models.ImageField(upload_to='images/')),
                ('description', models.CharField(max_length=1000)),
                ('department', models.CharField(max_length=100)),
                ('status', models.CharField(max_length=50)),
                ('location', models.CharField(max_length=100)),
                ('city', models.CharField(max_length=50)),
            ],
        ),
        migrations.CreateModel(
            name='Users',
            fields=[
                ('user_id', models.AutoField(auto_created=True, primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=100)),
                ('phone', models.CharField(max_length=10)),
            ],
        ),
        migrations.DeleteModel(
            name='Person',
        ),
        migrations.DeleteModel(
            name='Species',
        ),
        migrations.AddField(
            model_name='complaints',
            name='user_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, to='api.users'),
        ),
    ]